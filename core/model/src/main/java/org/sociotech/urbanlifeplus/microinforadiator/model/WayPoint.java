/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author vituslehner 12.08.17
 */
public class WayPoint {

    private final double lat;
    private final double lng;

    public WayPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lat", lat)
                .add("lng", lng)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WayPoint wayPoint = (WayPoint) o;
        return lat == wayPoint.lat &&
                lng == wayPoint.lng;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lat, lng);
    }
}
