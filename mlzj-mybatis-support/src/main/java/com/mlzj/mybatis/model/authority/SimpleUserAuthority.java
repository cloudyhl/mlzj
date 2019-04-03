package cn.wine.micropos.model.user;

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
    private Map<String, String> authority = new HashMap<>();


    @Override
    public Map<String, String> getAuthority() {
        return this.authority;
    }

    public void setAuthority(Map<String, String> authority) {
        this.authority = authority;
    }
}
