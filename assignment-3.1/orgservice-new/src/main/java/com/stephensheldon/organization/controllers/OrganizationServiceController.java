package com.stephensheldon.organization.controllers;

import com.stephensheldon.organization.model.Organization;
import com.stephensheldon.organization.services.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Stephen Sheldon
 **/

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationServiceController {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceController.class);

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value="/{organizationId}")
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {

        logger.debug("Looking up data for org {}",organizationId );

        return organizationService.getOrg(organizationId);
    }

    @PutMapping(value="/{organizationId}")
    public void updateOrganization(@PathVariable("organizationId") String organizationId,
                                   @RequestBody Organization organization) {
        organizationService.updateOrg(organization);
    }

    @PostMapping(value="/{organizationId}")
    public void saveOrganization(@RequestBody Organization organization) {
        organizationService.saveOrg(organization);
    }

    @DeleteMapping(value="/{organizationId}")
    public void deleteOrganization(@PathVariable("organizationId") String organizationId,
                                   @RequestBody Organization organization) {
        organizationService.deleteOrg(organization);
    }
}
