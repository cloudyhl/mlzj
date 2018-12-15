package com.mlzj.mlzjsecurity.entity;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;

/**
 * @author yhl
 * @date 2018/12/13
 */
public class ClientRegistration {

    private String registrationId;
    private String clientId;
    private String clientSecret;
    private ClientAuthenticationMethod clientAuthenticationMethod;
    private AuthorizationGrantType authorizationGrantType;
    private String redirectUriTemplate;
    private Set<String> scopes;
    private ProviderDetails providerDetails;
    private String clientName;

    public String getRegistrationId() {
        return registrationId;
    }

    public ClientRegistration setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public ClientRegistration setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public ClientRegistration setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public ClientAuthenticationMethod getClientAuthenticationMethod() {
        return clientAuthenticationMethod;
    }

    public ClientRegistration setClientAuthenticationMethod(ClientAuthenticationMethod clientAuthenticationMethod) {
        this.clientAuthenticationMethod = clientAuthenticationMethod;
        return this;
    }

    public AuthorizationGrantType getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public ClientRegistration setAuthorizationGrantType(AuthorizationGrantType authorizationGrantType) {
        this.authorizationGrantType = authorizationGrantType;
        return this;
    }

    public String getRedirectUriTemplate() {
        return redirectUriTemplate;
    }

    public ClientRegistration setRedirectUriTemplate(String redirectUriTemplate) {
        this.redirectUriTemplate = redirectUriTemplate;
        return this;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public ClientRegistration setScopes(Set<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public ProviderDetails getProviderDetails() {
        return providerDetails;
    }

    public ClientRegistration setProviderDetails(ProviderDetails providerDetails) {
        this.providerDetails = providerDetails;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public ClientRegistration setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }
}
