/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity;

/**
 * @author vituslehner 03.07.17
 */
public enum Proximity {
    NEAR,
    FAR;

    public static Proximity fromString(String value) {
        if ("near".equalsIgnoreCase(value)) {
            return NEAR;
        } else if ("far".equalsIgnoreCase(value)) {
            return FAR;
        } else {
            throw new IllegalArgumentException("Illegal proximity value: " + value);
        }
    }
}
