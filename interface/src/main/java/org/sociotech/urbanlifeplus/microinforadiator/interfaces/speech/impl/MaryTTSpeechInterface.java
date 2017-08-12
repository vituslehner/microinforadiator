/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.speech.impl;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sound.sampled.AudioInputStream;

/**
 * @author vituslehner 11.08.17
 */
@Component
public class MaryTTSpeechInterface {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MaryTTSpeechInterface() {
        MaryInterface marytts = null;
        try {
            marytts = new LocalMaryInterface();

            //marytts.setLocale(Locale.GERMAN);

            AudioInputStream audio = marytts.generateAudio("Hello World of micro information radiators.");
            AudioPlayer player = new AudioPlayer(audio);
            player.start();
            player.join();

        } catch (MaryConfigurationException e) {
            logger.error("Mary error 1: ", e);
        } catch (InterruptedException e) {
            logger.error("Mary error 2: ", e);
        } catch (SynthesisException e) {
            logger.error("Mary error 3: ", e);
        }
    }

}
