package com.mlzj.commontest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.commontest.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yhl
 * @date 2019/4/19
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 更新用户信息
     *
     * @param userName 用户名
     * @param age      年龄
     * @param address  地址
     */
    void updateUser(@Param("userName") String userName, @Param("age") Integer age, @Param("address") String address);

    /**
     * 批量更新
     *
     * @param users 用户集合
     */
    void updateBatch(@Param("users") List<User> users);
}
