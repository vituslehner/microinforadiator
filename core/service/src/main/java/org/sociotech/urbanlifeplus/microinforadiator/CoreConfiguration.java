/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
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

    private List<String> neighbouredMirs;

    public List<String> getNeighbouredMirs() {
        if(neighbouredMirs == null && neighbouredMirsRaw != null) {
            neighbouredMirs = Arrays.asList(neighbouredMirsRaw.split(","));
        }
        return neighbouredMirs;
    }
}
