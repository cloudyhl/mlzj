package com.mlzj.cloud.auth.config;

import com.alibaba.fastjson.JSON;
import com.mlzj.cloud.auth.common.constants.AuthConstants;
import com.mlzj.cloud.auth.model.SimpleUserDetail;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * @author yhl
 * @date 2019/7/25
 */
public class JwtAccessToken extends JwtAccessTokenConverter {


    /**
     * 生成token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        // 设置额外用户信息
        SimpleUserDetail baseUser = (SimpleUserDetail) authentication.getPrincipal();
        baseUser.setPassword(null);
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put(AuthConstants.USER_INFO, baseUser);

        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(AuthConstants.USER_INFO,convertUserData(map.get(AuthConstants.USER_INFO)));

    }

    private SimpleUserDetail convertUserData(Object map) {
        String json = JSON.toJSONString(map);
        return JSON.parseObject(json, SimpleUserDetail.class);
    }
}
