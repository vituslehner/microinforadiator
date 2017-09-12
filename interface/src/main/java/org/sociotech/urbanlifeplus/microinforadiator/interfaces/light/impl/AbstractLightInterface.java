/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightInterface;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightPhase;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author vituslehner 03.07.17
 */
public abstract class AbstractLightInterface implements LightInterface {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Collection<LightPhase> phases = new HashSet<>();

    @Override
    public void setPhases(Collection<LightPhase> colors) {
        this.phases = colors.stream().distinct().collect(Collectors.toCollection(HashSet::new));

        update();
    }

    @Override
    public void switchOff() {
        phases = new HashSet<>();

        update();
    }

    public Collection<LightPhase> getPhases() {
        return phases;
    }

    protected abstract void update();
}
