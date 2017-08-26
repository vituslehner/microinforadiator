/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.sociotech.urbanlifeplus.microinforadiator.interfaces.audio.SpeechInterface;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 26.08.17
 */
@Service
public class SpeechService {

    private final SpeechInterface speechInterface;

    public SpeechService(SpeechInterface speechInterface) {
        this.speechInterface = speechInterface;
    }

    public void speak(String text) {
        speechInterface.speak(text);
    }
}
