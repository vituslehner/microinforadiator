/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * @author vituslehner 04.07.17
 */
public class Route {

    private final List<WayPoint> wayPoints;

    public Route(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("wayPoints", wayPoints)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equal(wayPoints, route.wayPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(wayPoints);
    }
}
