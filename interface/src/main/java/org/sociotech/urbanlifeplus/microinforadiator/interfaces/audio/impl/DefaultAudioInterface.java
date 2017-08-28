/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.audio.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.audio.AudioInterface;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author vituslehner 26.08.17
 */
@Component
public class DefaultAudioInterface implements AudioInterface, LineListener {


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean playCompleted;

    public DefaultAudioInterface() {
        this.playCompleted = true;
    }

    @Override
    public void playPositiveSound() {
        playSound("positive");
    }

    @Override
    public void playNegativeSound() {
        playSound("negative");
    }

    private void playSound(String name) {
        logger.debug("Playing sound {}.", name);
        waitForCompletion();
        logger.debug("Playing sound {} now.", name);

        try {
            InputStream audioFile = getClass().getResourceAsStream("/media/sound_" + name + ".wav");
            InputStream bufferedIn = new BufferedInputStream(audioFile);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(audioStream);
            clip.start();
            playCompleted = false;

            waitForCompletion();

            clip.close();

        } catch (UnsupportedAudioFileException e) {
            logger.error("The specified audio file is not supported.", e);
        } catch (LineUnavailableException e) {
            logger.error("Audio line for playing back is unavailable.", e);
        } catch (IOException e) {
            logger.error("Error playing the audio file.", e);
        }
    }

    private void waitForCompletion() {
        while (!playCompleted) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread sleep error.", e);
            }
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            logger.debug("Sound started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            logger.debug("Sound stopped.");
        }

    }

    @PreDestroy
    private void disposeProcess() {
    }
}
