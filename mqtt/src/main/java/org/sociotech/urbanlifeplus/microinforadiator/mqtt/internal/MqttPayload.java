/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * @author vituslehner 12.07.17
 */
public class MqttPayload {

    private final String mirSourceId;
    private final String className;
    private final Object rawData;
    private final List<String> mirPath;
    private final int recursionDepth;

    @JsonCreator
    public MqttPayload(@JsonProperty("mirSourceId") String mirSourceId,
                       @JsonProperty("className") String className,
                       @JsonProperty("rawData") Object rawData,
                       @JsonProperty("mirPath") List<String> mirPath,
                       @JsonProperty("recursionDepth") int recursionDepth) {
        this.mirSourceId = mirSourceId;
        this.className = className;
        this.rawData = rawData;
        this.mirPath = mirPath;
        this.recursionDepth = recursionDepth;
    }

    public String getMirSourceId() {
        return mirSourceId;
    }

    public String getClassName() {
        return className;
    }

    public Object getRawData() {
        return rawData;
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
                .add("mirSourceId", mirSourceId)
                .add("className", className)
                .add("rawData", rawData)
                .add("mirPath", mirPath)
                .add("recursionDepth", recursionDepth)
                .toString();
    }
}
