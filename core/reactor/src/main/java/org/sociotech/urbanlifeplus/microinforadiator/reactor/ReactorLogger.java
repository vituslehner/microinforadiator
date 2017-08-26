/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.reactor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.ReactorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Utility reactor to log all reactor events.
 *
 * @author vituslehner 12.07.17
 */
@Service
public class ReactorLogger {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReactorLogger.class);

    private final EventBus reactorEventBus;

    @Autowired
    public ReactorLogger(@Qualifier("reactorEventBus") EventBus reactorEventBus) {
        this.reactorEventBus = reactorEventBus;
        this.reactorEventBus.register(this);
    }

    @Subscribe()
    public void log(ReactorEvent event) {
        LOGGER.info("REACTOR EVENT: {}", event);
    }
}
