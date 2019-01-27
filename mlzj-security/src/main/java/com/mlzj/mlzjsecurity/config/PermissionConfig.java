package com.mlzj.mlzjsecurity.config;

import com.google.common.collect.Maps;
import com.mlzj.common.utils.CharacterUtils;
import com.mlzj.mlzjsecurity.entity.SysPermission;
import com.mlzj.mlzjsecurity.entity.SysRole;
import com.mlzj.mlzjsecurity.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**权限校验实现
 * @author yhl
 * @date 2018/12/13
 */
@Slf4j
@Configuration
public class PermissionConfig implements PermissionEvaluator {
    /**匿名*/
    private static final String ANONYMOUS_USER ="anonymousUser";
    /**
     * get方法前缀
     */
    private static final String GET_METHOD_PREFIX = "get";
    /**
     * 角色名字段
     */
    private static final String ROLE = "roleName";

    /**
     * 权限名字段
     */
    private static final String PERMISSION_NAME = "name";

    /**
     * 系统角色或权限
     */
    private static final String AUTHENTICATION = "SysAuthentication";
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(authentication.getPrincipal().toString().compareToIgnoreCase(ANONYMOUS_USER) != 0){
            SysUser sysUser = (SysUser)authentication.getPrincipal();
            try{
                List<SysRole> roleList = sysUser.getRoleList();
                Map<String, Object> roleResult = checkAuthentication(roleList, SysRole.class, ROLE, targetDomainObject);
                if (roleResult.get(AUTHENTICATION) != null){
                    Map<String, Object> permissionResult = checkAuthentication(((SysRole) roleResult.get(AUTHENTICATION)).getPermissionList(), SysPermission.class, PERMISSION_NAME, permission);
                    return (boolean)permissionResult.get("result");
                }
            }catch (Exception e){
                log.error("Failure of privilege matching",e);
                return false;
            }

        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }

    /**
     *
     * @param authentications 角色或权限的集合
     * @param clazz 具体类型
     * @param fieldName 需要校验的字段名
     * @param compareObj 校验的值
     * @param <T> 具体类型泛型
     * @return 包含具体包含的对象及是否校验成功
     */
    private <T> Map<String,Object> checkAuthentication(List<T> authentications, Class<T> clazz, String fieldName,Object compareObj){
        HashMap<String, Object> resultMap = new HashMap<>(4);
        try {
            for (T authentication : authentications){
                Method getMethod = clazz.getDeclaredMethod(GET_METHOD_PREFIX + CharacterUtils.toUpperCaseFist(fieldName));
                Object value = getMethod.invoke(authentication);
                if (Objects.equals(value,compareObj)){
                    resultMap.put(AUTHENTICATION,authentication);
                    resultMap.put("result",true);
                    return resultMap;
                }
            }

        } catch (Exception e) {
            log.error("check authentication fail",e);
        }
        return resultMap;
    }
}
