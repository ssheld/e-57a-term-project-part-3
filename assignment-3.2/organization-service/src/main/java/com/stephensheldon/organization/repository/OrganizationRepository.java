package com.stephensheldon.organization.repository;

import com.stephensheldon.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Stephen Sheldon
 **/

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Organization findById(String organizationId);
}