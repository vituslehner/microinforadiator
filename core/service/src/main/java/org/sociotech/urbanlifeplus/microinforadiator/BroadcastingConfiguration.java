/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator;

import org.sociotech.urbanlifeplus.microinforadiator.mqtt.MqttDynamicConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vituslehner 18.07.17
 */
@Configuration
public class BroadcastingConfiguration {

    public final static String TOPIC_BASE = "ulp/mir/";

    private final CoreConfiguration coreConfiguration;

    public BroadcastingConfiguration(CoreConfiguration coreConfiguration) {
        this.coreConfiguration = coreConfiguration;
    }

    @Bean
    public MqttDynamicConfiguration mqttDynamicConfiguration() {
        List<String> topicSubscriptions =
                coreConfiguration.getNeighbouredMirs()
                        .stream()
                        .map(t -> TOPIC_BASE + t)
                        .collect(Collectors.toList());
        topicSubscriptions.add(getSourceTopic());
        return new MqttDynamicConfiguration(coreConfiguration.getMirId(), topicSubscriptions, getSourceTopic());
    }

    /**
     * The topic to broadcast events to based on the local MIR id.
     *
     * @return the MQTT topic to broadcast to
     */
    public String getSourceTopic() {
        return TOPIC_BASE + coreConfiguration.getMirId();
    }
}
