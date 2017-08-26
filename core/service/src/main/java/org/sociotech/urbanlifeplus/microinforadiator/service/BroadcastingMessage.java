/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * @author vituslehner 12.07.17
 */
public class BroadcastingMessage {

    private final Object rawData;
    private final String className;
    private final String topic;
    private final String mirSourceId;
    private final List<String> mirPath;
    private final int recursionDepth;

    @JsonCreator
    public BroadcastingMessage(@JsonProperty("rawData") Object rawData,
                               @JsonProperty("className") String className,
                               @JsonProperty("topic") String topic,
                               @JsonProperty("mirSourceId") String mirSourceId,
                               @JsonProperty("mirPath") List<String> mirPath,
                               @JsonProperty("recursionDepth") int recursionDepth) {
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
