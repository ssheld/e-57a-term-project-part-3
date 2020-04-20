package com.stephensheldon.organization.services;

import com.stephensheldon.organization.model.Organization;

/**
 * Author: Stephen Sheldon
 **/
public interface OrganizationService {

    Organization getOrg(String organizationId);

    void saveOrg(Organization organization);

    void updateOrg(Organization organization);

    void deleteOrg(Organization organization);
}
