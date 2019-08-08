package com.mlzj.cloud.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author yhl
 * @date 2019/7/25
 */
@Data
@TableName("mlzj_cloud_auth_client")
public class SimpleClientDetail implements ClientDetails {

    private static final long serialVersionUID = -4889346240093853308L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 资源id
     */
    private Set<String> resourceIds;


    @TableField(exist = false)
    private boolean secretRequired;

    /**
     * 客户端密码
     */
    private String clientSecret;


    @TableField(exist = false)
    private boolean scope;

    /**
     * 授权作用域
     */
    private Set<String> scopes;

    /**
     * 授权模式
     */
    private Set<String> authorizedGrantTypes;

    /**
     * 重定向地址
     */
    private Set<String> registeredRedirectUri;


    private Collection<GrantedAuthority> authorities;

    /**
     * token校验时间
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 重刷token
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 自动确认
     */
    private boolean autoApprove;

    /**
     * 附加信息
     */
    private Map<String,Object> additionalInformation;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return this.scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
