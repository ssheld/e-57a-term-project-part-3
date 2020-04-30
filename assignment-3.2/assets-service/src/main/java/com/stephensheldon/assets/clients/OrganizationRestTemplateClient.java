package com.stephensheldon.assets.clients;

import com.stephensheldon.assets.model.Organization;
import com.stephensheldon.assets.repository.OrganizationRedisRepository;
import com.stephensheldon.assets.utils.UserContext;
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

    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrganizationRedisRepository orgRedisRepo;

    private Organization checkRedisCache(String organizationId) {
        try {
            return orgRedisRepo.findOrganization(organizationId);
        } catch (Exception e) {
            logger.error("Error retrieving organization {} from Redis Cache. Exception: {}", organizationId, e);
            return null;
        }
    }

    private void cacheOrganizationObject(Organization org) {
        try {
            orgRedisRepo.saveOrganization(org);
        } catch (Exception e) {
            logger.error("Unable to cache organization {} in Redis. Exception: {}", org.getId(), e);
        }
    }

    public Organization getOrganization(String organizationId) {
        logger.debug("In Assets Service.getOrganization: {} ", UserContext.getCorrelationId());

        Organization org = checkRedisCache(organizationId);

        if (org != null) {
            logger.debug("Successfully retrieved organization {} from the redis cache: {}", organizationId, org);
            return org;
        }

        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);

        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://zuulservice/api/organization/v1/organizations/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        /*Save the record from cache*/
        org = restExchange.getBody();

        if (org != null) {
            cacheOrganizationObject(org);
        }
        return org;
    }
}