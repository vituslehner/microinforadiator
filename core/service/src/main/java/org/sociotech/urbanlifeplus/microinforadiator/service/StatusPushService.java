/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.MirStatusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author vituslehner 12.08.17
 */
@Service
public class StatusPushService implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusPushService.class);

    private static final int PUSH_PERIOD = 7;

    private final TimingService timingService;
    private final LightService lightService;
    private final CoreConfiguration coreConfiguration;
    private final EventBus reactorEventBus;

    @Autowired
    public StatusPushService(TimingService timingService,
                             LightService lightService,
                             CoreConfiguration coreConfiguration,
                             @Qualifier("reactorEventBus") EventBus reactorEventBus) {
        this.timingService = timingService;
        this.lightService = lightService;
        this.coreConfiguration = coreConfiguration;
        this.reactorEventBus = reactorEventBus;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(coreConfiguration.getPushStatus()) {
            LOGGER.info("Status Pushing is enabled. Period: {}", PUSH_PERIOD);
            timingService.scheduleAtFixedrate(this::publishStatus, 0, PUSH_PERIOD, TimeUnit.SECONDS);
        }
    }

    private void publishStatus() {
        MirStatusEvent status = new MirStatusEvent(coreConfiguration.getMirId(), coreConfiguration.getMirPositionAsWayPoint(), lightService.getCurrentColors());
        reactorEventBus.post(status);
    }
}
