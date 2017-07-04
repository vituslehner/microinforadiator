/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightInterface;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author vituslehner 03.07.17
 */
public abstract class AbstractLightInterface implements LightInterface {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Collection<LightColor> colors = new HashSet<>();

    @Override
    public void setColors(Collection<LightColor> colors) {
        this.colors = colors.stream().distinct().collect(Collectors.toCollection(HashSet::new));

        update();
    }

    @Override
    public void switchOff() {
        colors = new HashSet<>();

        update();
    }

    public Collection<LightColor> getColors() {
        return colors;
    }

    protected abstract void update();
}
