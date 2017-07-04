/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import org.sociotech.urbanlifeplus.microinforadiator.model.User;

/**
 * @author vituslehner 04.07.17
 */
public abstract class ReactorEvent {

    private final User user;

    public ReactorEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
