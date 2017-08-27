/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.reactor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;
import org.sociotech.urbanlifeplus.microinforadiator.model.Weather;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.UserProximityEvent;
import org.sociotech.urbanlifeplus.microinforadiator.service.SpeechService;
import org.sociotech.urbanlifeplus.microinforadiator.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author vituslehner 26.08.17
 */
@Service
public class WeatherReactor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherReactor.class);

    private final SpeechService speechService;
    private final WeatherService weatherService;
    private final CoreConfiguration coreConfiguration;
    private final EventBus reactorEventBus;

    public WeatherReactor(SpeechService speechService, WeatherService weatherService, CoreConfiguration coreConfiguration, EventBus reactorEventBus) {
        this.speechService = speechService;
        this.weatherService = weatherService;
        this.coreConfiguration = coreConfiguration;
        this.reactorEventBus = reactorEventBus;

        this.reactorEventBus.register(this);
    }

    @Subscribe
    public void proximityEvent(UserProximityEvent event) {
        LOGGER.debug("Weather Reactor received proximity: {}", event);
        if (event.getProximity() == Proximity.NEAR
                && Objects.equals(event.getSourceMirId(), coreConfiguration.getMirId())) {
            giveRecommendationBasedOnWeather(event.getUser());
        }
    }

    private void giveRecommendationBasedOnWeather(User user) {
        if (user == null) {
            return;
        }

        Weather weather = weatherService.getCurrentWeather();
        List<String> texts = new ArrayList<>();
        switch (weather) {
            case NONE:
                break;
            case RAIN:
                texts.add("Es wird Regen vorausgesagt. Nehmen Sie einen Schirm mit.");
                texts.add("Die Wetterdienste prognostizieren Regen. Stellen Sie sich darauf ein.");
                texts.add("Es soll bald regnen. Vielleicht möchten Sie zum Geschäft Angelikas Festmoden gehen, um trocken zu bleiben.");
                break;
            case STORM:
                texts.add("Passen Sie auf sich auf. Es ist ein Sturm angesagt.");
                texts.add("Es wird langsam stürmisch. Vielleicht suchen Sie sich eine Beschäftigung in einem Gebäude.");
                break;
            case SUN:
                texts.add("Heute ist es sehr sonnig. Geben Sie acht, dass Sie sich nicht verbrennen.");
                break;
            default:
                break;
        }

        if (texts.size() > 0) {
            Collections.shuffle(texts);
            String text = "Hallo " + user.getFirstName() + "! " + texts.get(0);

            speechService.speak(text);
        }
    }
}
