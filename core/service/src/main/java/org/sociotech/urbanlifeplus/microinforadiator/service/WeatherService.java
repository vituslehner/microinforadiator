/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.google.common.eventbus.Subscribe;
import org.sociotech.urbanlifeplus.microinforadiator.model.Weather;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.WeatherEvent;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 26.08.17
 */
@Service
public class WeatherService {

    private Weather currentWeather;

    public WeatherService() {
        this.currentWeather = Weather.NONE;
    }

    public Weather getCurrentWeather() {
        // normally here would an external weather service be asked
        return currentWeather;
    }

    @Subscribe
    public void listenToWeatherChanged(WeatherEvent event) {
        // in this simulation case the weather is broadcasted via MQTT -> reactor event bus
        currentWeather = event.getWeather();
    }
}
