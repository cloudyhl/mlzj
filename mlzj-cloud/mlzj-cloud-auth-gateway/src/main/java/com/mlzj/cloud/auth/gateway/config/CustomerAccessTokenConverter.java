package com.mlzj.cloud.auth.gateway.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

/**
 * @author yhl
 * @date 2019/8/6
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

	/**
	 * 添加额外信息
	 */
	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
		OAuth2Authentication authentication = super.extractAuthentication(claims);
		authentication.setDetails(claims);
		return authentication;
	}
}
