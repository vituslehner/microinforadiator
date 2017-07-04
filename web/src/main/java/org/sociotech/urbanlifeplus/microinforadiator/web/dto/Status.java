/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.web.dto;

/**
 * @author vituslehner 04.07.17
 */
public class Status {

    private final boolean success;
    private final String message;

    public Status(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Status(String message) {
        this.success = true;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
