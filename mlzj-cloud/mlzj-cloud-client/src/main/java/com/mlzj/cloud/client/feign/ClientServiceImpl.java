package com.mlzj.cloud.client.feign;

import com.mlzj.cloud.feign.service.client.ClientService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端服务实现
 * @author yhl
 * @date 2019/6/3
 */
@RestController
public class ClientServiceImpl implements ClientService {
    @Override
    public String selectUser() {
        System.out.println("jin  ru");
        return "user";
    }
}
