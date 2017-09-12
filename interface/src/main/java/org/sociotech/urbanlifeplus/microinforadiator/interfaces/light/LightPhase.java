/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces.light;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author vituslehner 12.09.17
 */
public class LightPhase {

    private final LightColor lightColor;
    private final LightSymbol lightSymbol;

    public LightPhase(LightColor lightColor, LightSymbol lightSymbol) {
        this.lightColor = lightColor;
        this.lightSymbol = lightSymbol;
    }

    public LightColor getLightColor() {
        return lightColor;
    }

    public LightSymbol getLightSymbol() {
        return lightSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LightPhase that = (LightPhase) o;
        return lightColor == that.lightColor &&
                lightSymbol == that.lightSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lightColor, lightSymbol);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lightColor", lightColor)
                .add("lightSymbol", lightSymbol)
                .toString();
    }
}
