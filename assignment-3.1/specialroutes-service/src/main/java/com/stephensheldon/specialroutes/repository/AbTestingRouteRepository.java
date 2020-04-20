package com.stephensheldon.specialroutes.repository;

import com.stephensheldon.specialroutes.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Stephen Sheldon
 **/

@Repository
public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute,String> {
    AbTestingRoute findByServiceName(String serviceName);
}
