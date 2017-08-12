/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightColor;

import java.util.Set;

/**
 * @author vituslehner 04.07.17
 */
public class User extends Cachable {

    private final String id;
    private final String firstName;
    private final String lastName;

    private Route route;
    private LightColor color;
    private Set<Impairment> impairments;


    @JsonCreator
    public User(@JsonProperty("id") String id,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.impairments = impairments;
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

    public LightColor getColor() {
        return color;
    }

    public void setColor(LightColor color) {
        this.color = color;
    }

    public Set<Impairment> getImpairments() {
        return impairments;
    }

    public void setImpairments(Set<Impairment> impairments) {
        this.impairments = impairments;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lifetime", getTimeToLive())
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("route", route)
                .add("color", color)
                .add("impairments", impairments)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) &&
                Objects.equal(firstName, user.firstName) &&
                Objects.equal(lastName, user.lastName) &&
                Objects.equal(route, user.route) &&
                Objects.equal(impairments, user.impairments) &&
                color == user.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, lastName, route, color, impairments);
    }
}
