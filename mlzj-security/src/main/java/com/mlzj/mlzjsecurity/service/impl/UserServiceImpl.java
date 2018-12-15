package com.mlzj.mlzjsecurity.service.impl;

import com.mlzj.mlzjsecurity.dao.UserRepository;
import com.mlzj.mlzjsecurity.entity.SysUser;
import com.mlzj.mlzjsecurity.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2018/12/12
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Cacheable(cacheNames = "authority", key = "#username")
    @Override
    public SysUser getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
