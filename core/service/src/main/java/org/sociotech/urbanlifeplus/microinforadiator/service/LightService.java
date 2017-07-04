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

    public void addColor(LightColor color) {
        currentColors.add(color);
        notifyInterfaces();
    }

    public void removeColor(LightColor color) {
        currentColors.remove(color);
        notifyInterfaces();
    }

    public Optional<LightColor> acquireSpareColor() {
        return Arrays.stream(LightColor.values())
                .filter(lightColor -> !currentColors.contains(lightColor))
                .findFirst();
    }

    private void notifyInterfaces() {
        lightInterfaces.forEach(lightInterface -> lightInterface.setColors(currentColors));
    }

}
