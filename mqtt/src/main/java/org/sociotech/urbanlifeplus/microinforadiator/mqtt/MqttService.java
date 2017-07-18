/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.sociotech.urbanlifeplus.microinforadiator.mqtt.internal.MqttGateway;
import org.sociotech.urbanlifeplus.microinforadiator.mqtt.internal.MqttPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author vituslehner 12.07.17
 */
@Service
public class MqttService implements MessageHandler {

    private MqttListener listener;
    private final MqttGateway mqttGateway;
    private final ObjectMapper jsonMapper;

    @Autowired
    public MqttService(MqttGateway mqttGateway) {
        this.mqttGateway = mqttGateway;
        listener = message -> {
        };
        this.jsonMapper = new ObjectMapper();
    }

    public MqttListener getListener() {
        return listener;
    }

    public void setListener(MqttListener listener) {
        Preconditions.checkNotNull(listener, "Listener must not be null.");
        this.listener = listener;
    }

    public void sendMessage(MqttMessage message) {
        try {
            MqttPayload payload = new MqttPayload(message.getMirSourceId(),
                    message.getClassName(),
                    message.getRawData(),
                    message.getMirPath(),
                    message.getRecursionDepth());
            String jsonPayload = jsonMapper.writeValueAsString(payload);
            mqttGateway.send(jsonPayload, message.getTopic());
        } catch (IOException e) {
            throw new MqttServiceException("Failed to write outgoing MQTT JSON payload. Payload: " + message.getRawData(), e);
        }
    }

    @Override
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            MqttPayload payload = jsonMapper.readValue((String) message.getPayload(), MqttPayload.class);
            MqttMessage inboundMessage = new MqttMessageBuilder()
                    .setRawData(payload.getRawData())
                    .setClassName(payload.getClassName())
                    .setTopic((String) message.getHeaders().get(MqttHeaders.TOPIC))
                    .setMirSourceId(payload.getMirSourceId())
                    .setMirPath(payload.getMirPath())
                    .setRecursionDepth(payload.getRecursionDepth())
                    .build();
            listener.handleMessage(inboundMessage);
        } catch (IOException e) {
            throw new MqttServiceException("Failed to read incoming JSON MQTT payload. Payload: " + message.getPayload(), e);
        }
    }
}
