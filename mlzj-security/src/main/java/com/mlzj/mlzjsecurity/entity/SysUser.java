package com.mlzj.mlzjsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yhl
 * @date 2018/12/11
 */
@Entity
public class SysUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 7771107448773721620L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户编号
     */
    private String usercode;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;
    /**
     * 用户角色
     */

    @ManyToMany(targetEntity = SysRole.class,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<SysRole> roleList = new ArrayList<>();

    /**
     * 是否启用
     */
    private boolean isEnabled;

    public Long getId() {
        return id;
    }

    public SysUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsercode() {
        return usercode;
    }

    public SysUser setUsercode(String usercode) {
        this.usercode = usercode;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoleList();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public SysUser setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public SysUser setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
        return this;
    }

    public SysUser setEnabled(boolean enabled) {
        isEnabled = enabled;
        return this;
    }

}
