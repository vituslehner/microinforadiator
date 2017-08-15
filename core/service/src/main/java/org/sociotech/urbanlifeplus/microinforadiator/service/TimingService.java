/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A service to schedule actions using different timing approaches, e.g. remove cachables after certain time or
 * remove lights after certain time.
 *
 * @author vituslehner 04.07.17
 */
@Service
public class TimingService {

    private final ScheduledExecutorService scheduledExecutorService;

    public TimingService() {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    public ScheduledFuture scheduleAtFixedrate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return this.scheduledExecutorService.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    @PreDestroy
    private void disposeExecutorService() {
        this.scheduledExecutorService.shutdown();
        if (!this.scheduledExecutorService.isShutdown()) {
            this.scheduledExecutorService.shutdownNow();
        }
    }
}
