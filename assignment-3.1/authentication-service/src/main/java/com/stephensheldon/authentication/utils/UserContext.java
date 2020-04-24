package com.stephensheldon.authentication.utils;

import org.springframework.stereotype.Component;

/**
 * Author: Stephen Sheldon
 **/

@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<>();

    public String getCorrelationId() {
        return correlationId.get();
    }

    public void setCorrelationId(String cid) {
        correlationId.set(cid);
    }

    public String getAuthToken() {
        return authToken.get();
    }

    public void setAuthToken(String aToken) {
        authToken.set(aToken);
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String aUser) {
        userId.set(aUser);
    }

    public String getOrgId() {
        return orgId.get();
    }

    public void setOrgId(String aOrg) {
        orgId.set(aOrg);
    }
}
