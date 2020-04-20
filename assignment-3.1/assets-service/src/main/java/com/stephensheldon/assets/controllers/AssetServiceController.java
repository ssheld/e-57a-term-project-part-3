package com.stephensheldon.assets.controllers;

import com.stephensheldon.assets.model.Asset;
import com.stephensheldon.assets.services.AssetService;
import com.stephensheldon.assets.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: Stephen Sheldon
 **/

@RestController
@RequestMapping(value="v1/organizations/{organizationId}/assets")
public class AssetServiceController {

    private static final Logger logger = LoggerFactory.getLogger(AssetServiceController.class);

    @Autowired
    private AssetService assetService;

    @Autowired
    private HttpServletRequest request;

    // Getter method to retrieve all assets belonging to organization
    @GetMapping(value="/")
    public List<Asset> getAssets(@PathVariable("organizationId") String organizationId) {
        logger.debug("AssetServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return assetService.getAssetsByOrg(organizationId);
    }

    // Getter method for asset with default client type
    @GetMapping(value="/{assetId}")
    public Asset getAssets(@PathVariable("organizationId") String organizationId,
                           @PathVariable("assetId") String assetId) throws InterruptedException {
        logger.debug("Found tmx-correlation-id in assets-service-controller: {} ", request.getHeader("tmx-correlation-id"));
        return assetService.findAssetById(organizationId, assetId);
    }

    // Put method to update asset currently in database
    @PutMapping(value="/{assetId}")
    public void updateAsset(@PathVariable("assetId") String assetId,
                            @RequestBody Asset asset) {
        assetService.updateAsset(asset);
    }

    // Post method to delete an asset from the database
    @PostMapping(value="/")
    public void saveAsset(@RequestBody Asset asset) {
        assetService.saveAsset(asset);
    }

    // Delete method to delete an asset from the database
    @DeleteMapping(value="/{assetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAsset(@PathVariable("assetId") String assetId,
                            @RequestBody Asset asset) {
        assetService.deleteAsset(asset);
    }
}