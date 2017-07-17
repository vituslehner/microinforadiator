/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.sociotech.urbanlifeplus.microinforadiator")
public class MicroInfoRadiatorApp {

    public static void main(String[] args) {
        SpringApplication.run(MicroInfoRadiatorApp.class, args);
    }

}
