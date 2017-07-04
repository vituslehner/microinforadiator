/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.web.simulation;

import com.google.common.eventbus.EventBus;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.ProximityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author vituslehner 04.07.17
 */
@Component
public class ProximitySimulation {

    private final EventBus interfaceEventBus;

    @Autowired
    public ProximitySimulation(@Qualifier("interfaceEventBus") EventBus interfaceEventBus) {
        this.interfaceEventBus = interfaceEventBus;
    }

    public void notify(String userId, String proximity) {
        ProximityEvent event = new ProximityEvent(userId, Proximity.fromString(proximity));
        interfaceEventBus.post(event);
    }
}
