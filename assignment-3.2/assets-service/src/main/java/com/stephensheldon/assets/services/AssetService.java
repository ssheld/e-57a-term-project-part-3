package com.stephensheldon.assets.services;

import com.stephensheldon.assets.model.Asset;

import java.util.List;

/**
 * Author: Stephen Sheldon
 **/
public interface AssetService {

    List<Asset> getAssetsByOrg(String OrganizationId);

    Asset findAssetById(String organizationId, String assetId);

    Asset saveAsset(Asset asset);

    Asset updateAsset(Asset asset);

    void deleteAsset(Asset asset);
}
