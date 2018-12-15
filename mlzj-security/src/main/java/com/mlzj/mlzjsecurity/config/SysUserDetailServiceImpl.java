package com.mlzj.mlzjsecurity.config;

import com.mlzj.mlzjsecurity.entity.SysUser;
import com.mlzj.mlzjsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yhl
 * @date 2018/12/12
 */
@Service
public class SysUserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) {
        SysUser sysUser = userService.getUserByName(userName);
        if (!Objects.isNull(sysUser)){
            return sysUser;
        }
        return null;
    }
}
