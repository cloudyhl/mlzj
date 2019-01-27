package com.mlzj.mlzjoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2018/12/15
 */
@Configuration
@EnableAuthorizationServer
public class MlzjAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private static final String DEMO_RESOURCE_ID = "order";
    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    RedisConnectionFactory redisConnectionFactory;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory().withClient("client_1")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .and().withClient("client_2")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret(new BCryptPasswordEncoder().encode("123456"));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)  {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager);
        //配置TokenService参数
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(endpoints.getTokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setClientDetailsService(endpoints.getClientDetailsService());
        tokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
        //30天
        tokenService.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        //50天
        tokenService.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(50));
        tokenService.setReuseRefreshToken(true);
        endpoints.tokenServices(tokenService);
    }



    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //允许表单认证
        //这里增加拦截器到安全认证链中，实现自定义认证，包括图片验证，短信验证，微信小程序，第三方系统，CAS单点登录
        oauthServer.allowFormAuthenticationForClients()
                   .tokenKeyAccess("permitAll()")
                   .checkTokenAccess("permitAll()");
    }

}



