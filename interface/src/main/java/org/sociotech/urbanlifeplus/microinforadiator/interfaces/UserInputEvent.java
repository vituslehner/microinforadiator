/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.interfaces;

/**
 * @author vituslehner 04.07.17
 */
public class UserInputEvent {

    private final String userId;

    public UserInputEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
