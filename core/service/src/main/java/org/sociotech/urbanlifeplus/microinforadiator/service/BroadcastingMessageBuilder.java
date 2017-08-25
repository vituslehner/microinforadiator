/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import java.util.List;

public class BroadcastingMessageBuilder {
    private Object rawData;
    private String className;
    private String topic;
    private String mirSourceId;
    private List<String> mirPath;
    private int recursionDepth;

    public BroadcastingMessageBuilder setRawData(Object rawData) {
        this.rawData = rawData;
        return this;
    }

    public BroadcastingMessageBuilder setClassName(String className) {
        this.className = className;
        return this;
    }

    public BroadcastingMessageBuilder setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public BroadcastingMessageBuilder setMirSourceId(String mirSourceId) {
        this.mirSourceId = mirSourceId;
        return this;
    }

    public BroadcastingMessageBuilder setMirPath(List<String> mirPath) {
        this.mirPath = mirPath;
        return this;
    }

    public BroadcastingMessageBuilder setRecursionDepth(int recursionDepth) {
        this.recursionDepth = recursionDepth;
        return this;
    }

    public BroadcastingMessage build() {
        return new BroadcastingMessage(rawData, className, topic, mirSourceId, mirPath, recursionDepth);
    }
}