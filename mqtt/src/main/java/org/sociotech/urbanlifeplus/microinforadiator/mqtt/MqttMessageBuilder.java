/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

import java.util.List;

public class MqttMessageBuilder {
    private Object rawData;
    private String className;
    private String topic;
    private String mirSourceId;
    private List<String> mirPath;
    private int recursionDepth;

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

    public MqttMessageBuilder setMirSourceId(String mirSourceId) {
        this.mirSourceId = mirSourceId;
        return this;
    }

    public MqttMessageBuilder setMirPath(List<String> mirPath) {
        this.mirPath = mirPath;
        return this;
    }

    public MqttMessageBuilder setRecursionDepth(int recursionDepth) {
        this.recursionDepth = recursionDepth;
        return this;
    }

    public MqttMessage build() {
        return new MqttMessage(rawData, className, topic, mirSourceId, mirPath, recursionDepth);
    }
}