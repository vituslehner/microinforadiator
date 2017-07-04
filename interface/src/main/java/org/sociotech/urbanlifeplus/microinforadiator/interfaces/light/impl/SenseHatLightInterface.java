/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.impl;

import org.springframework.stereotype.Component;

/**
 * @author vituslehner 03.07.17
 */
@Component
public class SenseHatLightInterface extends AbstractLightInterface {

    private static final String NAME = "Raspberry Pi Sense HAT Interface";

    public SenseHatLightInterface() {
        super();
    }

    @Override
    public String getName() {
        return NAME;
    }


    @Override
    protected void update() {

    }
}
