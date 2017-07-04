/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity;

import org.sociotech.urbanlifeplus.microinforadiator.interfaces.UserInputEvent;

/**
 * @author vituslehner 04.07.17
 */
public class ProximityEvent extends UserInputEvent {

    private final Proximity proximity;

    public ProximityEvent(String userId, Proximity proximity) {
        super(userId);
        this.proximity = proximity;
    }

    public Proximity getProximity() {
        return proximity;
    }
}
