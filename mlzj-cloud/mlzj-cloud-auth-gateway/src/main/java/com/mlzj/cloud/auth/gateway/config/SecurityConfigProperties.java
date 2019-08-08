package com.mlzj.cloud.auth.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 安全配置信息
 * @author yhl
 * @date 2019/8/2
 */
@ConfigurationProperties(prefix = "mlzj.uaa.security")
@Data
public class SecurityConfigProperties {

    private List<String> whiteList;

}
