package com.mlzj.cloud.auth.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author yhl
 * @date 2019/7/29
 */
@Data
public class SimpleGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -8468960714398248553L;

    @Override
    public String getAuthority() {
        return null;
    }
}
