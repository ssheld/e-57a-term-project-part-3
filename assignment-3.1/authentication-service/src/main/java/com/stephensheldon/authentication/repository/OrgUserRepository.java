package com.stephensheldon.authentication.repository;

import com.stephensheldon.authentication.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Stephen Sheldon
 **/

@Repository
public interface OrgUserRepository extends JpaRepository<UserOrganization, String> {
    UserOrganization findByUserName(String userName);
}
