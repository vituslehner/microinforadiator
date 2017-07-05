/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.web.dto;

import org.sociotech.urbanlifeplus.microinforadiator.model.User;

import java.util.List;

/**
 * @author vituslehner 05.07.17
 */
public class MirDeviceStatus extends Status {

    private final String id;

    private final List<User> currentUsers;

    public MirDeviceStatus(boolean success, String message, String id, List<User> currentUsers) {
        super(success, message);
        this.id = id;
        this.currentUsers = currentUsers;
    }

    public MirDeviceStatus(String message, String id, List<User> currentUsers) {
        super(message);
        this.id = id;
        this.currentUsers = currentUsers;
    }
}
