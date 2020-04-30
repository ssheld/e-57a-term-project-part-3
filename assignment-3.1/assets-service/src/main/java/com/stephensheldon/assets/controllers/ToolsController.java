package com.stephensheldon.assets.controllers;

import com.stephensheldon.assets.services.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: Stephen Sheldon
 **/

@RestController
@RequestMapping(value="v1/tools")
public class ToolsController {

    @Autowired
    private DiscoveryService discoveryService;

    @RequestMapping(value="/eureka/services", method = RequestMethod.GET)
    public List<String> getEurekaServices() {
        return discoveryService.getEurekaServices();
    }
}
