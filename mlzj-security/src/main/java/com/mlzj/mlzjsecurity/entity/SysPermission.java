package com.mlzj.mlzjsecurity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yhl
 * @date 2018/12/12
 */
@Entity
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 6901933909409864799L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限字符串
     */
    private String code;

    /**
     * 资源类型
     */
    private Integer type;

    /**
     * URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父菜单ID
     */
    private Long pid;

    public Long getId() {
        return id;
    }

    public SysPermission setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysPermission setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public SysPermission setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SysPermission setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SysPermission setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public SysPermission setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Long getPid() {
        return pid;
    }

    public SysPermission setPid(Long pid) {
        this.pid = pid;
        return this;
    }
}
