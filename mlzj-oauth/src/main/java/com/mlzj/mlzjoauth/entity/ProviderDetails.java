package com.mlzj.mlzjoauth.entity;

import java.util.Map;

/**
 * @author yhl
 * @date 2018/12/13
 */
public class ProviderDetails {
    private String authorizationUri;
    private String tokenUri;
    private UserInfoEndpoint userInfoEndpoint;
    private String jwkSetUri;
    private Map<String,Object> configurationMetadata;

    public String getAuthorizationUri() {
        return authorizationUri;
    }

    public ProviderDetails setAuthorizationUri(String authorizationUri) {
        this.authorizationUri = authorizationUri;
        return this;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public ProviderDetails setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
        return this;
    }

    public UserInfoEndpoint getUserInfoEndpoint() {
        return userInfoEndpoint;
    }

    public ProviderDetails setUserInfoEndpoint(UserInfoEndpoint userInfoEndpoint) {
        this.userInfoEndpoint = userInfoEndpoint;
        return this;
    }

    public String getJwkSetUri() {
        return jwkSetUri;
    }

    public ProviderDetails setJwkSetUri(String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
        return this;
    }

    public Map<String, Object> getConfigurationMetadata() {
        return configurationMetadata;
    }

    public ProviderDetails setConfigurationMetadata(Map<String, Object> configurationMetadata) {
        this.configurationMetadata = configurationMetadata;
        return this;
    }
}
