package com.mlzj.mlzjsecurity.entity;

import org.springframework.security.oauth2.core.AuthenticationMethod;

/**
 * @author yhl
 * @date 2018/12/13
 */
public class UserInfoEndpoint {

    private String uri;
    private AuthenticationMethod authenticationMethod;
    private String userNameAttributeName;

    public String getUri() {
        return uri;
    }

    public UserInfoEndpoint setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public AuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    public UserInfoEndpoint setAuthenticationMethod(AuthenticationMethod authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
        return this;
    }

    public String getUserNameAttributeName() {
        return userNameAttributeName;
    }

    public UserInfoEndpoint setUserNameAttributeName(String userNameAttributeName) {
        this.userNameAttributeName = userNameAttributeName;
        return this;
    }
}
