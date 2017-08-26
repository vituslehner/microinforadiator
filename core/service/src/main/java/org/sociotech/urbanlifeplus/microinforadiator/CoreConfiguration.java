/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import org.sociotech.urbanlifeplus.microinforadiator.model.WayPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author vituslehner 03.07.17
 */
@Configuration
public class CoreConfiguration {

    @Bean("reactorEventBus")
    public EventBus reactorEventBus() {
        return new EventBus("reactorEventBus");
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Value("${ULP_MIR_ID}")
    private String mirId;

    public String getMirId() {
        return mirId;
    }

    @Value("${ULP_MIR_POSITION}")
    private String mirPosition;

    public String getMirPosition() {
        return mirPosition;
    }

    public WayPoint getMirPositionAsWayPoint() {
        double lat = Double.parseDouble(mirPosition.split(",")[0].trim());
        double lng = Double.parseDouble(mirPosition.split(",")[1].trim());
        return new WayPoint(lat, lng);
    }

    @Value("${ULP_RECURSION_DEPTH:3}")
    private int recursionDepth;

    public int getRecursionDepth() {
        return recursionDepth;
    }

    @Value("${ULP_NEIGHBOURED_MIRS}")
    private String neighbouredMirsRaw;

    public String getNeighbouredMirsRaw() {
        return neighbouredMirsRaw;
    }

    @Value("${ULP_MIR_IBEACON_MAJOR}")
    private String iBeaconMajor;

    public String getIBeaconMajor() {
        return iBeaconMajor;
    }

    @Value("${ULP_MIR_IBEACON_MINOR}")
    private String iBeaconMinor;

    public String getIBeaconMinor() {
        return iBeaconMinor;
    }

    @Value("${ULP_PUSH_STATUS}")
    private boolean pushStatus;

    public boolean getPushStatus() {
        return pushStatus;
    }

    private List<String> neighbouredMirs;

    public List<String> getNeighbouredMirs() {
        if(neighbouredMirs == null && neighbouredMirsRaw != null) {
            neighbouredMirs = Arrays.asList(neighbouredMirsRaw.split(","));
        }
        return neighbouredMirs;
    }
}
