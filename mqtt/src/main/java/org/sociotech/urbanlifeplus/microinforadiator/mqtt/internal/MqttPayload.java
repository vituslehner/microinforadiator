/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * @author vituslehner 12.07.17
 */
public class MqttPayload {

    private final String mirSourceId;
    private final String className;
    private final Object rawData;

    @JsonCreator
    public MqttPayload(@JsonProperty("mirSourceId") String mirSourceId,
                       @JsonProperty("className") String className,
                       @JsonProperty("rawData") Object rawData) {
        this.mirSourceId = mirSourceId;
        this.className = className;
        this.rawData = rawData;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mirSourceId", mirSourceId)
                .add("className", className)
                .add("rawData", rawData)
                .toString();
    }
}
