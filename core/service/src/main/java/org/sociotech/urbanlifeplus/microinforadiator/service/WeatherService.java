/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.model.Weather;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.WeatherEvent;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 26.08.17
 */
@Service
public class WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    private final EventBus reactorEventBus;

    private Weather currentWeather;

    public WeatherService(EventBus reactorEventBus) {
        this.reactorEventBus = reactorEventBus;
        this.currentWeather = Weather.NONE;

        this.reactorEventBus.register(this);
    }

    public Weather getCurrentWeather() {
        // normally here would an external weather service be asked
        return currentWeather;
    }

    @Subscribe
    public void listenToWeatherChanged(WeatherEvent event) {
        LOGGER.debug("Changing current weather to {}.", event.getWeather());
        // in this simulation case the weather is broadcasted via MQTT -> reactor event bus
        currentWeather = event.getWeather();
    }
}
