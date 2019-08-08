//package com.mlzj.cloud.auth.gateway.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.annotation.Resource;
//
///**
// * @author yhl
// * @date 2019/8/2
// */
//@Configuration
//@EnableOAuth2Sso
//@Order(-1)
//public class WebConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private SecurityConfigProperties securityConfigProperties;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests().antMatchers(securityConfigProperties.getWhiteList().toArray(new String[0])).permitAll()
//            .anyRequest()
//            .authenticated();
//    }
//
//
//}
