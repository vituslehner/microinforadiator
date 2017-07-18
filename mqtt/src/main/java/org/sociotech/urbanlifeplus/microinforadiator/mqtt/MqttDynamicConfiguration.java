/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

import java.util.List;

/**
 * @author vituslehner 18.07.17
 */
public class MqttDynamicConfiguration {

    private final String mqttId;
    private final List<String> topicSubscriptions;
    private final String defaultPublishingTopic;

    public MqttDynamicConfiguration(String mqttId, List<String> topicSubscriptions, String defaultPublishingTopic) {
        this.mqttId = mqttId;
        this.topicSubscriptions = topicSubscriptions;
        this.defaultPublishingTopic = defaultPublishingTopic;
    }

    public String getMqttId() {
        return mqttId;
    }

    public List<String> getTopicSubscriptions() {
        return topicSubscriptions;
    }

    public String getDefaultPublishingTopic() {
        return defaultPublishingTopic;
    }
}
