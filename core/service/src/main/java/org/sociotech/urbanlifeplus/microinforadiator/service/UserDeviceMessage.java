/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;

/**
 * @author vituslehner 25.08.17
 */
public class UserDeviceMessage {

    private final User user;
    private final Proximity proximity;
    private final String mirIBeaconMajorMinor;

    @JsonCreator
    public UserDeviceMessage(@JsonProperty("user") User user,
                             @JsonProperty("proximity") Proximity proximity,
                             @JsonProperty("mirIBeaconMajorMinor") String mirIBeaconMajorMinor) {
        this.user = user;
        this.proximity = proximity;
        this.mirIBeaconMajorMinor = mirIBeaconMajorMinor;
    }

    public User getUser() {
        return user;
    }

    public Proximity getProximity() {
        return proximity;
    }

    public String getMirIBeaconMajorMinor() {
        return mirIBeaconMajorMinor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDeviceMessage that = (UserDeviceMessage) o;
        return Objects.equal(user, that.user) &&
                proximity == that.proximity &&
                Objects.equal(mirIBeaconMajorMinor, that.mirIBeaconMajorMinor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user, proximity, mirIBeaconMajorMinor);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("user", user)
                .add("proximity", proximity)
                .add("mirIBeaconMajorMinor", mirIBeaconMajorMinor)
                .toString();
    }
}
