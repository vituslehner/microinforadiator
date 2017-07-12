/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;

/**
 * @author vituslehner 04.07.17
 */
public class UserProximityEvent extends ReactorEvent {

    private final Proximity proximity;

    @JsonCreator
    public UserProximityEvent(@JsonProperty("user") User user,
                              @JsonProperty("mirId") String mirId,
                              @JsonProperty("proximity") Proximity proximity) {
        super(user, mirId);
        this.proximity = proximity;
    }

    public Proximity getProximity() {
        return proximity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("proximity", proximity)
                .add("user", getUser())
                .add("mirId", getMirId())
                .toString();
    }
}
