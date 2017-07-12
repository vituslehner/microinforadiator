/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.ProximityEvent;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.UserProximityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 03.07.17
 */
@Service
public class ProximityService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProximityService.class);

    private final EventBus interfaceEventBus;
    private final EventBus reactorEventBus;

    private final UserService userService;
    private final CoreConfiguration coreConfiguration;

    @Autowired
    public ProximityService(@Qualifier("interfaceEventBus") EventBus interfaceEventBus,
                            @Qualifier("reactorEventBus") EventBus reactorEventBus,
                            UserService userService, CoreConfiguration coreConfiguration) {
        this.interfaceEventBus = interfaceEventBus;
        this.reactorEventBus = reactorEventBus;
        this.userService = userService;
        this.coreConfiguration = coreConfiguration;

        this.interfaceEventBus.register(this);
    }


    @Subscribe
    public void update(ProximityEvent interfaceEvent) {
        LOGGER.debug("INTRFC: Received proximity event: {}", interfaceEvent);
        User user = userService.findUserbyId(interfaceEvent.getUserId());

        UserProximityEvent reactorEvent = new UserProximityEvent(user, coreConfiguration.getId(), interfaceEvent.getProximity());
        reactorEventBus.post(reactorEvent);
    }

}
