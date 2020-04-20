package com.stephensheldon.assets.model;


import javax.persistence.*;

/**
 * Author: Stephen Sheldon
 **/

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @Column(name = "asset_id", nullable = false)
    private String id;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "comment")
    private String comment;

    @Transient
    private String organizationName = "";

    @Transient
    private String contactName = "";

    @Transient
    private String contactEmail = "";

    @Transient
    private String contactPhone = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Asset withId(String assetId) {
        this.setId(assetId);
        return this;
    }

    public Asset withAssetName(String assetName) {
        this.setAssetName(assetName);
        return this;
    }

    public Asset withOrganizationName(String organizationName) {
        this.setOrganizationName(organizationName);
        return this;
    }

    public Asset withOrganizationId(String organizationId) {
        this.setOrganizationId(organizationId);
        return this;
    }

    public Asset withContactName(String contactName) {
        this.setContactName(contactName);
        return this;
    }

    public Asset withContactEmail(String contactEmail) {
        this.setContactEmail(contactEmail);
        return this;
    }

    public Asset withContactPhone(String contactPhone) {
        this.setContactPhone(contactPhone);
        return this;
    }

    public Asset withComment(String comment) {
        this.setComment(comment);
        return this;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", assetName='" + assetName + '\'' +
                ", assetType='" + assetType + '\'' +
                ", comment='" + comment + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }
}
