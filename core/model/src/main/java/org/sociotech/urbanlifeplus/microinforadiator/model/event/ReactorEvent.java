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
    private final String sourceMirId;

    public ReactorEvent(User user, String sourceMirId) {
        this.user = user;
        this.sourceMirId = sourceMirId;
    }

    public User getUser() {
        return user;
    }

    public String getSourceMirId() {
        return sourceMirId;
    }
}
