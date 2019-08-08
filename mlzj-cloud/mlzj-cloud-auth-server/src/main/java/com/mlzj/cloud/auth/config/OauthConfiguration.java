package com.mlzj.cloud.auth.config;

import com.mlzj.cloud.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/6/9
 */
@Configuration
@EnableAuthorizationServer
public class OauthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserService userService;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    private static final String KEY_PASS = "mlzjauth";

    private static final String KEY_PAIR = "mlzj";

    private static final String KEY_STORE_PATH = "keystore.jks";


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("mlzj-zuul")
                .secret(new BCryptPasswordEncoder().encode("mlzj-zuul"))
                .redirectUris("http://localhost:20022/login")
                .scopes("WRIGHT", "read")
                .authorities("WRIGHT_READ", "WRIGHT_WRITE")
                .accessTokenValiditySeconds(7200)
                .refreshTokenValiditySeconds(7200)
                .authorizedGrantTypes("refresh_token", "authorization_code","password").autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(getRedisTokenStore())
                .tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter())
                .userDetailsService(userService)
                // 允许使用Get和Post方法访问端口
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Bean
    public RedisTokenStore getRedisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    /**
     * @param security 用来配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // 主要是让/oauth/token支持client_id以及client_secret作登录认证 要进行client校验就必须配置这个
                .allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter converter = new JwtAccessToken();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource(KEY_STORE_PATH), KEY_PASS.toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(KEY_PAIR));

        return converter;
    }

}
