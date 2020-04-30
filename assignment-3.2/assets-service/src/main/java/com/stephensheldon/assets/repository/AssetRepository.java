package com.stephensheldon.assets.repository;

import com.stephensheldon.assets.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Author: Stephen Sheldon
 **/

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {

    Optional<List<Asset>> findByOrganizationId(String organizationId);

    Optional<Asset> findByAssetIdAndOrganizationId(String assetId, String organizationId);
}