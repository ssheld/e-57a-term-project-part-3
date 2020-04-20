package com.stephensheldon.assets.clients;

import com.stephensheldon.assets.model.Organization;
import com.stephensheldon.assets.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Stephen Sheldon
 **/

@Component
public class OrganizationRestTemplateClient {

    private static Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    @Autowired
    RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        logger.debug(">>> In Assets Service.getOrganization: {}. Thread Id: {}", UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange("http://zuulservice/api/organization/v1/organizations/{organizationId}",
                                      HttpMethod.GET, null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
