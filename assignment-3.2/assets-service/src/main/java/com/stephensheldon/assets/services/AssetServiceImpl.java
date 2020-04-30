package com.stephensheldon.assets.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stephensheldon.assets.clients.OrganizationRestTemplateClient;
import com.stephensheldon.assets.config.ServiceConfig;
import com.stephensheldon.assets.model.Asset;
import com.stephensheldon.assets.model.Organization;
import com.stephensheldon.assets.repository.AssetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author: Stephen Sheldon
 **/

@Service
public class AssetServiceImpl implements AssetService {

    private static Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class);

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    ServiceConfig config;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    // Method to simulate a delayed call
    private void randomLongRun() {
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        logger.info("Random number is " + randomNum);

        if (randomNum == 3)
            sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @HystrixCommand
    @Override
    public Asset findAssetById(String organizationId, String assetId) {

        Optional<Asset> asset = assetRepository.findByAssetIdAndOrganizationId(assetId, organizationId);

        Organization organization = getOrganization(organizationId);

        logger.info(organization.toString());

        return asset.get()
                .withOrganizationName(organization.getOrganizationName())
                .withContactName(organization.getContactName())
                .withContactEmail(organization.getContactEmail())
                .withContactPhone(organization.getContactPhone())
                .withComment(config.getExampleProperty());
    }

    // Wrap method call in default with Hystrix in default values
    @HystrixCommand
    private Organization getOrganization(String organizationId) {

        return organizationRestClient.getOrganization(organizationId);
    }

    @HystrixCommand(fallbackMethod = "buildFallbackAssetList",
            threadPoolKey = "assetByOrgThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")},
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")}
    )
    @Override
    public List<Asset> getAssetsByOrg(String organizationId) {

        randomLongRun();

        return assetRepository.findByOrganizationId(organizationId).get();
    }

    @HystrixCommand
    @Override
    public Asset saveAsset(Asset asset) {
        asset.withId(UUID.randomUUID().toString());

        return assetRepository.save(asset);
    }

    @HystrixCommand
    @Override
    public void deleteAsset(Asset asset) {
        assetRepository.delete(asset.getAssetId());
    }

    @HystrixCommand
    @Override
    public Asset updateAsset(Asset asset) {

        Optional<Asset> foundAsset = assetRepository.findByAssetIdAndOrganizationId(asset.getAssetId(), asset.getOrganizationId());

        if (foundAsset.isPresent()) {
            if (asset.getAssetName() != null) {
                foundAsset.get().setAssetName(asset.getAssetName());
            }
            if (asset.getAssetType() != null) {
                foundAsset.get().setAssetType(asset.getAssetType());
            }
            if (asset.getComment() != null) {
                foundAsset.get().setComment(asset.getComment());
            }
        }
        return assetRepository.save(foundAsset.get());
    }

    /* Hystrix fallback strategy to return an asset with no information */
    private List<Asset> buildFallbackAssetList(String organizationId) {
        List<Asset> fallbackList = new ArrayList<>();
        Asset asset = new Asset()
                .withId("0000000-00-00000")
                .withOrganizationId(organizationId)
                .withAssetName("Sorry no assets information currently available");

        fallbackList.add(asset);

        return fallbackList;
    }
}
