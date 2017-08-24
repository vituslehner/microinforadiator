/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Service to update the lighting interfaces. Light colors applied to this service are automatically
 * broadcasted to all light interfaces.
 *
 * @author vituslehner 03.07.17
 */
@Service
public class LightService {

    private final Collection<LightInterface> lightInterfaces;
    private final Collection<LightColor> currentColors;

    @Autowired
    public LightService(Collection<LightInterface> lightInterfaces) {
        this.lightInterfaces = lightInterfaces;
        this.currentColors = new HashSet<>();
    }

    /**
     * Add a color to the lights.
     *
     * @param color the color
     */
    public void addColor(LightColor color) {
        currentColors.add(color);
        notifyInterfaces();
    }

    /**
     * Remove a color from the lights.
     *
     * @param color the color
     */
    public void removeColor(LightColor color) {
        currentColors.remove(color);
        notifyInterfaces();
    }

    /**
     * Returns an {@link Optional} that contains a color that is not in use yet.
     *
     * @return a spare color
     */
    public Optional<LightColor> acquireSpareColor() {
        return Arrays.stream(LightColor.values())
                .filter(lightColor -> !currentColors.contains(lightColor))
                .findFirst();
    }

    /**
     * Notify light interfaces about changes.
     */
    private void notifyInterfaces() {
        lightInterfaces.forEach(lightInterface -> lightInterface.setColors(currentColors));
    }

    public Collection<LightColor> getCurrentColors() {
        return currentColors;
    }
}
