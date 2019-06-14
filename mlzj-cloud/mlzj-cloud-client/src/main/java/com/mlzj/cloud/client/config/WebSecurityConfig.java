//package com.mlzj.cloud.client.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
///**
// * @author yhl
// * @date 2019/6/9
// */
//@Configuration
//@EnableResourceServer
//public class WebSecurityConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .antMatchers("/addUser").hasAuthority("WRIGTH_READ");
//
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources){
//        resources.resourceId("WRIGTH")
//                .tokenStore(jwtTokenStore());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setSigningKey("cloudyhl");
//        return jwtAccessTokenConverter;
//    }
//
//    @Bean
//    public JwtTokenStore jwtTokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//}
