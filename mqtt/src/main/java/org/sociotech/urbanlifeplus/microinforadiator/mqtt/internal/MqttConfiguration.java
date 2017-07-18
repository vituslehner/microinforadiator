/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt.internal;

import org.sociotech.urbanlifeplus.microinforadiator.mqtt.MqttDynamicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author vituslehner 12.07.17
 */
@Configuration
public class MqttConfiguration {

    @Value("${MQTT_BROKER_HOST}")
    private String brokerHost;

    @Value("${MQTT_BROKER_PORT}")
    private String brokerPort;

    @Value("${MQTT_BROKER_USERNAME}")
    private String brokerUsername;

    @Value("${MQTT_BROKER_PASSWORD}")
    private String brokerPassword;

    @Autowired
    private MqttDynamicConfiguration dynamicConfiguration;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs("tcp://" + brokerHost + ":" + brokerPort);
        factory.setUserName(brokerUsername);
        factory.setPassword(brokerPassword);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("mir_consumer_client_" + dynamicConfiguration.getMqttId(),
                        mqttClientFactory(),
                        dynamicConfiguration.getTopicSubscriptions().toArray(new String[]{}));
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("mir_producer_client_" + dynamicConfiguration.getMqttId(), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(dynamicConfiguration.getDefaultPublishingTopic());
        return messageHandler;
    }

}
