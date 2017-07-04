/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author vituslehner 04.07.17
 */
@Configuration
public class InterfaceConfiguration {

    @Bean("interfaceEventBus")
    public EventBus interfaceEventBus() {
        return new EventBus("interfaceEventBus");
    }

}
