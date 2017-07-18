/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.service.UserService;
import org.sociotech.urbanlifeplus.microinforadiator.web.dto.MirDeviceStatus;
import org.sociotech.urbanlifeplus.microinforadiator.web.dto.Status;
import org.sociotech.urbanlifeplus.microinforadiator.web.simulation.ProximitySimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author vituslehner 03.07.17
 */
@RestController
@RequestMapping("/rest")
public class MirRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MirRestController.class);

    private final ProximitySimulation proximitySimulation;

    private final CoreConfiguration coreConfiguration;
    private final UserService userService;

    @Autowired
    public MirRestController(ProximitySimulation proximitySimulation, CoreConfiguration coreConfiguration, UserService userService) {
        this.proximitySimulation = proximitySimulation;
        this.coreConfiguration = coreConfiguration;
        this.userService = userService;
    }

    @RequestMapping("/proximity/{proximity}")
    public ResponseEntity simulateProximity(@RequestParam("userId") String userId, @PathVariable("proximity") String proximity) {
        LOGGER.debug("REST: Simulating proximity {} for user {}.", proximity, userId);
        proximitySimulation.notify(userId, proximity);

        return new ResponseEntity<Status>(new Status("Notified listeners about near user."), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<MirDeviceStatus> getStatus() {
        MirDeviceStatus status = new MirDeviceStatus("", coreConfiguration.getMirId(), userService.getCurrentUsers());

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
