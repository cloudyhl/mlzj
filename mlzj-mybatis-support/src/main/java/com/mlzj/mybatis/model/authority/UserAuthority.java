package cn.wine.micropos.model.user;

import java.util.Map;

/**
 * 用户权限接口
 * @author yhl
 * @date 2019/3/5
 */
public interface UserAuthority {

    /**
     * 获取用户的权限，用于生成sql
     * @return 返回用户的权限对，key为数据库表的column，value为该列的值
     */
    Map<String,String> getAuthority();
}
