package com.stephensheldon.specialroutes.controllers;

import com.stephensheldon.specialroutes.model.AbTestingRoute;
import com.stephensheldon.specialroutes.services.AbTestingRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Stephen Sheldon
 **/

@RestController
@RequestMapping(value="v1/route/")
public class SpecialRoutesServiceController {

    @Autowired
    AbTestingRouteService routeService;

    @GetMapping(value="abtesting/{serviceName}")
    public AbTestingRoute abstestings(@PathVariable("serviceName") String serviceName) {

        return routeService.getRoute( serviceName);
    }
}