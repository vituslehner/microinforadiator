/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.web;

import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author vituslehner 03.07.17
 */
@Controller
public class MirWebController {

    private final CoreConfiguration coreConfiguration;

    private final WebConfiguration webConfiguration;

    @Autowired
    public MirWebController(CoreConfiguration coreConfiguration, WebConfiguration webConfiguration) {
        this.coreConfiguration = coreConfiguration;
        this.webConfiguration = webConfiguration;
    }

    @GetMapping("/")
    public ModelAndView index(ModelMap model) {
        model.addAttribute("redirect", "index");
        return new ModelAndView("redirect:/overview", model);
    }

    @RequestMapping("/overview")
    public String overview(Model model) {
        model.addAttribute("mir_id", coreConfiguration.getId());

        if (webConfiguration.isMaster()) {
            model.addAttribute("message", " | This MIR is not configured to act as web node.");
        }

        return "overview";
    }

}
