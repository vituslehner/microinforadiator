/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.sociotech.urbanlifeplus.microinforadiator.interfaces.speech.AudioInterface;
import org.springframework.stereotype.Service;

/**
 * @author vituslehner 26.08.17
 */
@Service
public class EmotionService {

    private final AudioInterface audioInterface;

    public EmotionService(AudioInterface audioInterface) {
        this.audioInterface = audioInterface;
    }

    public void givePositiveFeedbackSignal() {
        audioInterface.playPositiveSound();
    }

    public void giveNegativeFeedbackSignal() {
        audioInterface.playNegativeSound();
    }
}
