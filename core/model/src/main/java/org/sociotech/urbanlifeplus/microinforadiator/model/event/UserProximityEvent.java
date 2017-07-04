/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import com.google.common.base.MoreObjects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;

/**
 * @author vituslehner 04.07.17
 */
public class UserProximityEvent extends ReactorEvent {

    private final Proximity proximity;

    public UserProximityEvent(User user, Proximity proximity) {
        super(user);
        this.proximity = proximity;
    }

    public Proximity getProximity() {
        return proximity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("user", getUser())
                .add("proximity", proximity)
                .toString();
    }
}
