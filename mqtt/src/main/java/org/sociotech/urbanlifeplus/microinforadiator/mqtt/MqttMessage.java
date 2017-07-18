/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * @author vituslehner 12.07.17
 */
public class MqttMessage {

    private final Object rawData;
    private final String className;
    private final String topic;
    private final String mirSourceId;
    private final List<String> mirPath;
    private final int recursionDepth;

    public MqttMessage(Object rawData, String className, String topic, String mirSourceId, List<String> mirPath, int recursionDepth) {
        this.rawData = rawData;
        this.className = className;
        this.topic = topic;
        this.mirSourceId = mirSourceId;
        this.mirPath = mirPath;
        this.recursionDepth = recursionDepth;
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

    public List<String> getMirPath() {
        return mirPath;
    }

    public int getRecursionDepth() {
        return recursionDepth;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("rawData", rawData)
                .add("className", className)
                .add("topic", topic)
                .add("mirSourceId", mirSourceId)
                .add("mirPath", mirPath)
                .add("recursionDepth", recursionDepth)
                .toString();
    }
}
