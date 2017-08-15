/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;
import org.sociotech.urbanlifeplus.microinforadiator.model.WayPoint;

import java.util.Set;

/**
 * @author vituslehner 12.08.17
 */
public class MirStatusEvent {

    private final String mirId;
    private final WayPoint position;
    private final Set<LightColor> lightColors;

    public MirStatusEvent(String mirId, WayPoint position, Set<LightColor> lightColors) {
        this.mirId = mirId;
        this.position = position;
        this.lightColors = lightColors;
    }

    public String getMirId() {
        return mirId;
    }

    public WayPoint getPosition() {
        return position;
    }

    public Set<LightColor> getLightColors() {
        return lightColors;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mirId", mirId)
                .add("position", position)
                .add("lightColors", lightColors)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MirStatusEvent that = (MirStatusEvent) o;
        return Objects.equal(mirId, that.mirId) &&
                Objects.equal(position, that.position) &&
                Objects.equal(lightColors, that.lightColors);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mirId, position, lightColors);
    }
}
