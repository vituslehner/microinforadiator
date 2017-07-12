/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

/**
 * @author vituslehner 12.07.17
 */
public class MqttServiceException extends RuntimeException {

    public MqttServiceException(String message) {
        super(message);
    }

    public MqttServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
