/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author vituslehner 03.07.17
 */
@Configuration
public class CoreConfiguration {

    @Bean("reactorEventBus")
    public EventBus interfaceEventBus() {
        return new EventBus("reactorEventBus");
    }

    @Value("${ULP_MIR_ID}")
    private String id;

    public String getId() {
        return id;
    }
}
