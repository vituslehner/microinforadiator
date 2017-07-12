/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.mqtt;

/**
 * @author vituslehner 12.07.17
 */
public interface MqttListener {

    void handleMessage(MqttMessage message);

}
