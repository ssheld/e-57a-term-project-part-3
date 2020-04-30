package com.stephensheldon.assets.repository;

import com.stephensheldon.assets.model.Organization;

/**
 * Author: Stephen Sheldon
 **/

public interface OrganizationRedisRepository {
    void saveOrganization(Organization org);

    void updateOrganization(Organization org);

    void deleteOrganization(String organizationId);

    Organization findOrganization(String organizationId);
}
