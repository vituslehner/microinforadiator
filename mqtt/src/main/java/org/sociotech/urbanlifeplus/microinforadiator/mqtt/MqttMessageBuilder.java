/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

public class MqttMessageBuilder {
    private Object rawData;
    private String className;
    private String topic;
    private String sourceMirId;

    public MqttMessageBuilder setRawData(Object rawData) {
        this.rawData = rawData;
        return this;
    }

    public MqttMessageBuilder setClassName(String className) {
        this.className = className;
        return this;
    }

    public MqttMessageBuilder setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public MqttMessageBuilder setSourceMirId(String sourceMirId) {
        this.sourceMirId = sourceMirId;
        return this;
    }

    public MqttMessage build() {
        return new MqttMessage(rawData, className, topic, sourceMirId);
    }
}