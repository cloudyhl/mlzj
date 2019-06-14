package com.mlzj.cloud.feign.fallback;

import com.mlzj.cloud.feign.service.client.ClientService;
import org.springframework.stereotype.Component;

/**
 * 客户端失败保护机制
 * @author yhl
 * @date 2019/6/3
 */
@Component
public class ClientServiceFallBackImpl implements ClientService {
    public String selectUser() {
        return "bad request and service was dead";
    }
}
