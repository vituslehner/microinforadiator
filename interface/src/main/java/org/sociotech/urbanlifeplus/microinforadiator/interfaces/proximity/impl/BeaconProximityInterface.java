/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.impl;

import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.ProximityInterface;
import org.springframework.stereotype.Component;

/**
 * @author vituslehner 03.07.17
 */
@Component
public class BeaconProximityInterface implements ProximityInterface {

    private static final String NAME = "iBeacon Proximity Interface";

    @Override
    public String getName() {
        return NAME;
    }

}
