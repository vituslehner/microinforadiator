/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.light;

import org.sociotech.urbanlifeplus.microinforadiator.interfaces.MirInterface;

import java.util.Collection;

/**
 * @author vituslehner 03.07.17
 */
public interface LightInterface extends MirInterface {

    void setColors(Collection<LightColor> colors);

    void switchOff();

}
