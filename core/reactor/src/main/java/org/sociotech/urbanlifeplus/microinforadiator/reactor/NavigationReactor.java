/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.reactor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.UserProximityEvent;
import org.sociotech.urbanlifeplus.microinforadiator.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity.FAR;
import static org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity.NEAR;

/**
 * Reactor that listens to internal and external proximity events and accordingly updates interfaces
 * to navigate users to their destinations.
 *
 * @author vituslehner 04.07.17
 */
@Service
public class NavigationReactor {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationReactor.class);

    private final LightService lightService;
    private final EventBus reactorEventBus;

    @Autowired
    public NavigationReactor(LightService lightService, @Qualifier("reactorEventBus") EventBus reactorEventBus) {
        this.lightService = lightService;
        this.reactorEventBus = reactorEventBus;
        this.reactorEventBus.register(this);
    }

    /**
     * Listen to reactor proximity events and update light and arrow interfaces accordingly.
     * Uses e.g. Google Maps APIs to calculate route information and identifier location on route.
     *
     * @param proximityEvent the proximity event
     */
    @Subscribe
    public void proximityEvent(UserProximityEvent proximityEvent) {
        LOGGER.debug("Receiving reactor proximity event: {}", proximityEvent);

        User user = proximityEvent.getUser();
        Proximity proximity = proximityEvent.getProximity();
        if (proximity == NEAR) {
            lightService.addColor(getOrCreateUserColor(user));
        } else if (proximity == FAR && user.getColor() != null) {
            lightService.removeColor(user.getColor());
        }
    }

    /**
     * Gets the user color or creates a new one if not done yet.
     *
     * @param user the user to create a color for
     * @return the user color
     */
    private LightColor getOrCreateUserColor(User user) {
        if (user.getColor() == null) {
            LightColor newColor = lightService.acquireSpareColor()
                    .orElseThrow(() -> new IllegalStateException("Could not acquire light color for user for navigational purposes."));
            user.setColor(newColor);
        }
        return user.getColor();
    }

}
