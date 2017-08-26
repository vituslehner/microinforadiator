/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;
import org.sociotech.urbanlifeplus.microinforadiator.model.Weather;

/**
 * @author vituslehner 26.08.17
 */
public class WeatherEvent extends ReactorEvent{

    private final Weather weather;

    @JsonCreator
    public WeatherEvent(@JsonProperty("user") User user,
                        @JsonProperty("mirId") String sourceMirId,
                        @JsonProperty("weather") Weather weather) {
        super(user, sourceMirId);
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherEvent that = (WeatherEvent) o;
        return weather == that.weather;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(weather);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("user", getUser())
                .add("sourceMirId", getSourceMirId())
                .add("weather", weather)
                .toString();
    }
}
