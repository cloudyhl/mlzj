package com.mlzj.cloud.auth;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

import com.mlzj.cloud.auth.model.SimpleClientDetail;
import com.mlzj.cloud.auth.service.ClientService;
import com.mlzj.cloud.auth.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzCloudAuthServerApplicationTests {
    @Resource
    private UserService userService;

    @Resource
    private ClientService clientService;

    @Test
    public void contextLoads() {
        UserDetails zhangsan = userService.loadUserByUsername("zhangsan");
        System.out.println(zhangsan);
    }


    @Test
    public void encode(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("mlzj-zuul");
        System.out.println(encode);
    }

    @Test
    public void saveClient(){
        SimpleClientDetail simpleClientDetail = new SimpleClientDetail();
        simpleClientDetail.setClientId("mlzj-zuul");
        simpleClientDetail.setSecretRequired(true);
        simpleClientDetail.setClientSecret("$2a$10$uoBOTugjrsw8Z1Dmktnhlut79RnDDAPgVwt9JyL48YaiqAoKlH/LG");
        simpleClientDetail.setScope(false);
        Set<String> authorizedGrantTypes = new HashSet<>(4);
        authorizedGrantTypes.add("authorization_code");
        authorizedGrantTypes.add("refresh_token");
        authorizedGrantTypes.add("password");
        simpleClientDetail.setAuthorizedGrantTypes(authorizedGrantTypes);
        Set<String> registeredRedirectUris = new HashSet<>();
        registeredRedirectUris.add("http://localhost:20021");
        simpleClientDetail.setRegisteredRedirectUri(registeredRedirectUris);
        simpleClientDetail.setAccessTokenValiditySeconds(7200);
        simpleClientDetail.setRefreshTokenValiditySeconds(7200);
        simpleClientDetail.setAutoApprove(true);
        simpleClientDetail.setAdditionalInformation(null);
        clientService.saveSimpleClientDetail(simpleClientDetail);

    }

    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("keystore.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, "mlzjauth".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("mlzj", "mlzjauth".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateToken(String subject, int expirationSeconds) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public static String parseToken(String token) {
        String subject = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }


    @Test
    public void jwt(){
        String s = generateToken("abc", 7200);
        System.out.println(s);
    }
}
