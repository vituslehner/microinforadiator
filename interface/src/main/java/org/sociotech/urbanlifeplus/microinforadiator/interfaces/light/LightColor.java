/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.light;


import java.awt.*;

/**
 * @author vituslehner 03.07.17
 */
public enum LightColor {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    PINK(Color.PINK),
    ORANGE(Color.ORANGE);

    private Color color;

    LightColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
