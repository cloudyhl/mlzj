package com.mlzj.mlzjsecurity.config;

import com.mlzj.mlzjsecurity.entity.SysPermission;
import com.mlzj.mlzjsecurity.entity.SysRole;
import com.mlzj.mlzjsecurity.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author yhl
 * @date 2018/12/13
 */
@Slf4j
@Configuration
public class PermissionConfig implements PermissionEvaluator {
    /**匿名*/
    private static final String ANONYMOUS_USER ="anonymousUser";
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean flag = false;
        if(authentication.getPrincipal().toString().compareToIgnoreCase(ANONYMOUS_USER) != 0){
            SysUser sysUser = (SysUser)authentication.getPrincipal();
            SysRole currentRole = null;
            try{
                List<SysRole> roleList = sysUser.getRoleList();
                for (SysRole sysRole:roleList){
                    if (StringUtils.equals(targetDomainObject.toString(),sysRole.getRoleName())){
                        currentRole = sysRole;
                        break;
                    }
                }
                if (!Objects.isNull(currentRole)){
                    for (SysPermission sysPermission:currentRole.getPermissionList()){
                        if (StringUtils.equals(permission.toString(),sysPermission.getName())){
                            flag = true;
                            break;
                        }
                    }
                }
            }catch (Exception e){
                log.error("Failure of privilege matching",e);
                return false;
            }

        }
        return flag;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
