package com.mlzj.cloud.auth.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.mlzj.cloud.auth.common.constants.AuthConstants;
import com.mlzj.cloud.auth.gateway.config.SecurityConfigProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * @author yhl
 * @date 2019/7/31
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Resource
    private SecurityConfigProperties securityConfigProperties;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        boolean isIgnore = false;
        for (String whitelist : securityConfigProperties.getWhiteList()) {
            isIgnore = this.isIgnore(request, whitelist);
        }
        if (isIgnore){
            return null;
        }
        requestContext.addZuulRequestHeader(AuthConstants.USER_HEADER,getUserInfo());
        return null;
    }

    /**
     * 如果是白名单中的请求,则不需要携带token
     * 是否忽略
     * @return 是否忽略
     */
    private boolean isIgnore(HttpServletRequest request, String whiteList){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(whiteList, request.getRequestURI());
    }

    private String getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        LinkedHashMap decodedDetails = (LinkedHashMap) oAuth2AuthenticationDetails.getDecodedDetails();
        Object userInfo = decodedDetails.get(AuthConstants.USER_INFO);
        return JSONObject.toJSONString(userInfo);
    }
}
