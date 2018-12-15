package com.mlzj.mlzjsecurity.dao;

import com.mlzj.mlzjsecurity.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author yhl
 * @date 2018/12/12
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser,Long>, UserDao, JpaSpecificationExecutor<SysUser> {
    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 系统用户
     */
    SysUser findByUsername(String userName);
}
