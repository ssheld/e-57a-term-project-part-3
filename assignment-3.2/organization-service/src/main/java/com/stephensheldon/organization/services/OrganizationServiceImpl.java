package com.stephensheldon.organization.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stephensheldon.organization.events.source.SimpleSourceBean;
import com.stephensheldon.organization.model.Organization;
import com.stephensheldon.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Author: Stephen Sheldon
 **/

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository orgRepository;

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    @HystrixCommand
    @Override
    public Organization getOrg(String organizationId) {
        return orgRepository.findById(organizationId);
    }

    @HystrixCommand
    @Override
    public void saveOrg(Organization organization) {
        organization.setId(UUID.randomUUID().toString());

        orgRepository.save(organization);
        simpleSourceBean.publishOrgChange("SAVE", organization.getId());
    }

    @HystrixCommand
    @Override
    public void updateOrg(Organization organization) {
        orgRepository.save(organization);
        simpleSourceBean.publishOrgChange("UPDATE", organization.getId());
    }

    @HystrixCommand
    @Override
    public void deleteOrg(String organizationId) {
        orgRepository.delete(organizationId);
        simpleSourceBean.publishOrgChange("DELETE", organizationId);
    }
}