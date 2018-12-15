package com.mlzj.mlzjsecurity.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yhl
 * @date 2018/12/11
 */
@Entity
public class SysRole implements Serializable {

    private static final long serialVersionUID = -1193319562246511694L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色拥有的权限
     */
    @ManyToMany(targetEntity = SysPermission.class,fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private List<SysPermission> permissionList = new ArrayList<>();

    public SysRole() {
    }

    public SysRole(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public SysRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public SysRole setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public SysRole setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
        return this;
    }
}
