/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;

/**
 * @author vituslehner 04.07.17
 */
public class User extends Cachable {

    private final String id;
    private final String firstName;
    private final String lastName;

    private Route route;

    private Proximity proximity;
    private LightColor color;


    @JsonCreator
    public User(@JsonProperty("id") String id,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Proximity getProximity() {
        return proximity;
    }

    public void setProximity(Proximity proximity) {
        this.proximity = proximity;
    }

    public LightColor getColor() {
        return color;
    }

    public void setColor(LightColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lifetime", getLifetime())
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("route", route)
                .add("proximity", proximity)
                .add("color", color)
                .toString();
    }
}
