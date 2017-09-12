/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightInterface;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightPhase;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightSymbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service to update the lighting interfaces. Light colors applied to this service are automatically
 * broadcasted to all light interfaces.
 *
 * @author vituslehner 03.07.17
 */
@Service
public class LightService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LightService.class);

    private final Collection<LightInterface> lightInterfaces;
    private final Collection<LightPhase> currentPhases;

    @Autowired
    public LightService(Collection<LightInterface> lightInterfaces) {
        this.lightInterfaces = lightInterfaces;
        this.currentPhases = Collections.synchronizedCollection(new HashSet<>());

        notifyInterfaces();
    }

    /**
     * Add a color to the lights.
     *
     * @param color  the color
     * @param lightSymbol the lightSymbol
     */
    public void addPhase(LightColor color, LightSymbol lightSymbol) {
        currentPhases.add(new LightPhase(color, lightSymbol));
        notifyInterfaces();
    }

    /**
     * Add a color to the lights.
     *
     * @param color the color
     */
    public void addPhase(LightColor color) {
        currentPhases.add(new LightPhase(color, LightSymbol.NONE));
        notifyInterfaces();
    }

    /**
     * Remove a color from the lights.
     *
     * @param color the color
     */
    public void removeColorPhase(LightColor color) {
        Optional<LightPhase> phase =
                currentPhases.stream()
                        .filter(p -> Objects.equals(color, p.getLightColor()))
                        .findFirst();

        if (phase.isPresent()) {
            currentPhases.remove(phase.get());
            notifyInterfaces();
        }
    }

    /**
     * Notify light interfaces about changes.
     */
    private void notifyInterfaces() {
        lightInterfaces.forEach((lightInterface) -> {
            try {
                lightInterface.setPhases(currentPhases);
            } catch (Exception e) {
                LOGGER.error("Failed to notify light interface about light color changes.", e);
            }
        });
    }

    public Collection<LightPhase> getCurrentPhases() {
        return currentPhases;
    }
}
