package com.mlzj.mlzjsecurity.dao.impl;

import com.mlzj.jpa.common.CommonRepositoryBean;
import com.mlzj.mlzjsecurity.dao.UserDao;
import com.mlzj.mlzjsecurity.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


/**
 * @author yhl
 * @date 2018/12/12
 */
@Repository
public class UserDaoImpl extends CommonRepositoryBean<SysUser,Long> implements UserDao {

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        super(SysUser.class, entityManager);
    }

}
