//package com.mlzj.cloud.zuul.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 安全认证
// * @author yhl
// * @date 2019/6/8
// */
//@Configuration
//@EnableOAuth2Sso
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeRequests().antMatchers("/login").permitAll()
//                .anyRequest().authenticated().and().csrf().disable();
//    }
//}
