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
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.ReactorEvent;
import org.sociotech.urbanlifeplus.microinforadiator.mqtt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

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
    private final EventBus reactorEventBus;
    private final ObjectMapper objectMapper;

    @Autowired
    public BroadcastingService(MqttService mqttService, CoreConfiguration coreConfiguration, @Qualifier("reactorEventBus") EventBus reactorEventBus, ObjectMapper objectMapper) {
        this.mqttService = mqttService;
        this.coreConfiguration = coreConfiguration;
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
            if (message.getClassName() != null
                    && message.getClassName().startsWith("org.sociotech.urbanlifeplus.microinforadiator.model.event.")
                    && !Objects.equals(message.getMirSourceId(), coreConfiguration.getId())) {
                Class<?> sourceClass = Class.forName(message.getClassName());
                Object event = objectMapper.readValue(message.getRawData().toString(), sourceClass);
                reactorEventBus.post(event);
            } else {
                LOGGER.error("Bad deserialization class name given! Possible HACKING attempt! Message: {}", message);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not find class to deserialize JSON message: {}", message);
        } catch (IOException e) {
            LOGGER.error("Could not parse JSON data: {}", message, e);
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
            if (Objects.equals(event.getMirId(), coreConfiguration.getId())) {
                LOGGER.debug("Broadcasting reactor event: {}", event);
                String jsonData = objectMapper.writeValueAsString(event);
                MqttMessage message = new MqttMessageBuilder()
                        .setRawData(jsonData)
                        .setClassName(event.getClass().getName())
                        .setSourceMirId(coreConfiguration.getId())
                        .setTopic(getSourceTopic())
                        .build();
                mqttService.sendMessage(message);
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not generate JSON from event: {}", event, e);
        } catch (MqttServiceException e) {
            LOGGER.error("Could not broadcast message for event: {}", event, e);
        }
    }

    /**
     * The topic to broadcast events to based on the local MIR id.
     *
     * @return the MQTT topic to broadcast to
     */
    public String getSourceTopic() {
        return "ulp/mir/" + coreConfiguration.getId();
    }
}
