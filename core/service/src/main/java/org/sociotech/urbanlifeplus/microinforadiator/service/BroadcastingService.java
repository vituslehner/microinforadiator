/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.BroadcastingConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.ReactorEvent;
import org.sociotech.urbanlifeplus.microinforadiator.mqtt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Broadcasting service receives messages from MQTT bus and generates and submits reactor events from it.
 * Also, events that were submitted to the reactor locally are submitted to the MQTT bus to be broadcasted
 * to other listeners.
 *
 * @author vituslehner 12.07.17
 */
@Service
public class BroadcastingService implements MqttListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(BroadcastingService.class);

    private final MqttService mqttService;
    private final CoreConfiguration coreConfiguration;
    private final BroadcastingConfiguration broadcastingConfiguration;
    private final EventBus reactorEventBus;
    private final ObjectMapper objectMapper;

    @Autowired
    public BroadcastingService(MqttService mqttService,
                               CoreConfiguration coreConfiguration,
                               BroadcastingConfiguration broadcastingConfiguration,
                               @Qualifier("reactorEventBus") EventBus reactorEventBus,
                               ObjectMapper objectMapper) {
        this.mqttService = mqttService;
        this.coreConfiguration = coreConfiguration;
        this.broadcastingConfiguration = broadcastingConfiguration;
        this.reactorEventBus = reactorEventBus;
        this.objectMapper = objectMapper;

        this.reactorEventBus.register(this);
        this.mqttService.setListener(this);
    }

    /**
     * Handle messages coming from MQTT bus and convert and submit them to reactor event bus.
     *
     * @param message the MQTT message coming in
     */
    @Override
    public void handleMessage(MqttMessage message) {
        try {
            validateMessage(message);
            if (!isLocalMirInPath(message)) {
                if (message.getRecursionDepth() > 0) {
                    redirectMessage(message);
                }
                Object event = deserializeObject(message.getRawData(), message.getClassName());
                reactorEventBus.post(event);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not find class to deserialize JSON message: {}", message);
        } catch (IOException e) {
            LOGGER.error("Could not parse JSON data: {}", message, e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Could not deserialize content of message: {}", message, e);
        }
    }

    /**
     * Handle events from the reactor event bus and broadcast them via MQTT if their source is the local application.
     *
     * @param event the reactor event that might be broadcasted
     */
    @Subscribe
    public void broadcastReactorEvent(ReactorEvent event) {
        try {
            if (isLocalEvent(event) && coreConfiguration.getRecursionDepth() > 0) {
                LOGGER.debug("Broadcasting reactor event: {}", event);
                convertAndSendEvent(event);
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not generate JSON from event: {}", event, e);
        } catch (MqttServiceException e) {
            LOGGER.error("Could not broadcast message for event: {}", event, e);
        }
    }

    private Object deserializeObject(Object rawData, String className) throws ClassNotFoundException, IOException {
        if (!className.startsWith("org.sociotech.urbanlifeplus.microinforadiator.model.event.")) {
            throw new IllegalArgumentException("Cannot deserialize object. Illegal package (possible hacking attempt). Class name: " + className);
        }

        Class<?> sourceClass = Class.forName(className);
        return objectMapper.readValue(rawData.toString(), sourceClass);
    }

    private void validateMessage(MqttMessage message) {
        checkNotNull(message.getMirSourceId(), "mirSourceId must not be null.");
        checkNotNull(message.getMirPath(), "mirPath must not be null.");
        checkNotNull(message.getRawData(), "rawData must not be null.");
        checkNotNull(message.getTopic(), "topic must not be null.");
        checkNotNull(message.getClassName(), "className must not be null.");
    }

    private boolean isLocalMirInPath(MqttMessage message) {
        return message.getMirPath().stream().anyMatch(p -> Objects.equals(p, coreConfiguration.getMirId()));
    }

    private void redirectMessage(MqttMessage message) {
        List<String> updatedMirPath = message.getMirPath();
        updatedMirPath.add(coreConfiguration.getMirId());
        MqttMessage redirectedMessage = new MqttMessageBuilder()
                .setRawData(message.getRawData())
                .setClassName(message.getClassName())
                .setMirSourceId(message.getMirSourceId())
                .setTopic(broadcastingConfiguration.getSourceTopic())
                .setMirPath(updatedMirPath)
                .setRecursionDepth(message.getRecursionDepth() - 1)
                .build();
        mqttService.sendMessage(redirectedMessage);
    }

    private void convertAndSendEvent(ReactorEvent event) throws JsonProcessingException {
        String jsonData = objectMapper.writeValueAsString(event);
        MqttMessage message = new MqttMessageBuilder()
                .setRawData(jsonData)
                .setClassName(event.getClass().getName())
                .setMirSourceId(coreConfiguration.getMirId())
                .setTopic(broadcastingConfiguration.getSourceTopic())
                .setMirPath(newArrayList(coreConfiguration.getMirId()))
                .setRecursionDepth(coreConfiguration.getRecursionDepth() - 1)
                .build();
        mqttService.sendMessage(message);
    }

    /**
     * Returns true if the origin of this event was the local MIR. Would be false if this
     * reactor event was originally broadcasted by a different MIR but submitted into the
     * reactor.
     *
     * @param event the event to check
     * @return true if event's origin is local
     */
    private boolean isLocalEvent(ReactorEvent event) {
        return Objects.equals(event.getMirId(), coreConfiguration.getMirId());
    }
}
