package com.stephensheldon.assets.events.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Stephen Sheldon
 **/
public class OrganizationChangeModel {

    private String type;
    private String action;
    private String organizationId;
    private String correlationId;

    // @JsonCreator and @JsonProperty are necessary for Jackson (de)serialization. Previous
    // code implemented a default constructor with explicit super() call. While this works, this
    // new approach gives us the option of making this an immutable object (If we were to
    // remove our property setter methods).
    @JsonCreator
    public OrganizationChangeModel(@JsonProperty("type") String type,
                                   @JsonProperty("action") String action,
                                   @JsonProperty("organizationId") String organizationId,
                                   @JsonProperty("correlationId") String correlationId) {
        super();
        this.type = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "OrganizationChangeModel{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
