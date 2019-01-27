package com.mlzj.mlzjsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务器
 * @author yhl
 * @date 2019/1/18
 */
@Configuration
@EnableResourceServer
public class MlzjResourceServer extends ResourceServerConfigurerAdapter {
    private static final String DEMO_RESOURCE_ID = "order";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }
    @Bean
    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("client_1");
        tokenServices.setClientSecret("123456");
        tokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:8080/oauth/check_token");
        tokenServices.setRestTemplate(restTemplate());
        tokenServices.setAccessTokenConverter(accessTokenConverter());
        return tokenServices;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Bean
    public RestTemplate restTemplate() {
        //httpRequestFactory()
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET));
        mediaTypes.add(new MediaType("text", "javascript",MappingJackson2HttpMessageConverter.DEFAULT_CHARSET));
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(new ObjectMapper());
                jsonConverter.setSupportedMediaTypes(mediaTypes);
            }
        }
        return restTemplate;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

}
