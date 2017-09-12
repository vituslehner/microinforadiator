/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightSymbol;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.SymbolEvent;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 12.09.17
 */
@Service
public class SymbolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    private final EventBus reactorEventBus;

    private LightSymbol currentSymbol;

    public SymbolService(EventBus reactorEventBus) {
        this.reactorEventBus = reactorEventBus;
        this.currentSymbol = LightSymbol.NONE;

        this.reactorEventBus.register(this);
    }

    public LightSymbol getCurrentSymbol() {
        // normally, this would be retrieved based on some logic
        return currentSymbol;
    }

    @Subscribe
    public void listenToWeatherChanged(SymbolEvent event) {
        LOGGER.debug("Changing current light symbol to {}.", event.getLightSymbol());
        currentSymbol = event.getLightSymbol();
    }

}
