/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.audio.SpeechInterface;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 26.08.17
 */
@Service
public class SpeechService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechInterface.class);

    private final SpeechInterface speechInterface;

    public SpeechService(SpeechInterface speechInterface) {
        this.speechInterface = speechInterface;
    }

    public void speak(String text) {
        LOGGER.debug("Speaking: {}", text);
        speechInterface.speak(text);
    }
}
