/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

import com.google.common.base.MoreObjects;

/**
 * @author vituslehner 12.07.17
 */
public class MqttMessage {

    private final Object rawData;
    private final String className;
    private final String topic;
    private final String mirSourceId;

    public MqttMessage(Object rawData, String className, String topic, String mirSourceId) {
        this.rawData = rawData;
        this.className = className;
        this.topic = topic;
        this.mirSourceId = mirSourceId;
    }

    public Object getRawData() {
        return rawData;
    }

    public String getClassName() {
        return className;
    }

    public String getTopic() {
        return topic;
    }

    public String getMirSourceId() {
        return mirSourceId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("rawData", rawData)
                .add("className", className)
                .add("topic", topic)
                .add("mirSourceId", mirSourceId)
                .toString();
    }
}
