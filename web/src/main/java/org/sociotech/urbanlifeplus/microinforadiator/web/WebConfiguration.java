/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author vituslehner 03.07.17
 */
@Configuration
public class WebConfiguration {

    @Value("${ULP_WEB_MASTER}")
    private boolean isMaster;

    @Value("${ULP_WEB_CHILDREN}")
    private String children;

    public boolean isMaster() {
        return isMaster;
    }

    public String getChildren() {
        return children;
    }
}
