package com.mlzj.cloud.auth.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author yhl
 * @date 2019/7/26
 */
@Configuration
@EnableResourceServer
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private SecurityConfigProperties securityConfigProperties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices());

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers(securityConfigProperties.getWhiteList().toArray(new String[0])).permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
            .httpBasic().and().csrf().disable();
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() throws IOException {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        org.springframework.core.io.Resource resource = new ClassPathResource("publicKey.key");
        String publicKey;
        publicKey = inputStream2String(resource.getInputStream());
        converter.setVerifierKey(publicKey);
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return converter;
    }
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() throws IOException {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    private String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    @Bean
    public TokenStore tokenStore() throws IOException {
        return new JwtTokenStore(accessTokenConverter());
    }

}
