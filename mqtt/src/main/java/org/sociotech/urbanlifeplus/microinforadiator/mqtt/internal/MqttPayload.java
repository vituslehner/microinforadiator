/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author vituslehner 12.07.17
 */
public class MqttPayload {

    private final String topic;
    private final String content;


    public MqttPayload(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MqttPayload that = (MqttPayload) o;
        return Objects.equal(topic, that.topic) &&
                Objects.equal(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(topic, content);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("topic", topic)
                .add("content", content)
                .toString();
    }
}
