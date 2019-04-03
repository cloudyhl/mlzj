package com.mlzj.mybatis.model.authority;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限用户信息
 *
 * @author yhl
 * @date 2019/3/5
 */
public class SimpleUserAuthority implements UserAuthority {

    /**
     * 权限的集合
     */
    private Map<String, Object> authority = new HashMap<>();


    @Override
    public Map<String, Object> getAuthority() {
        return this.authority;
    }

    public void setAuthority(Map<String, Object> authority) {
        this.authority = authority;
    }
}
