package com.mlzj.cloud.feign.service.client;

import com.mlzj.cloud.feign.fallback.ClientServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 客户端service
 * @author yhl
 * @date 2019/6/2
 */
@FeignClient(name = "mlzj-client",fallback = ClientServiceFallBackImpl.class)
public interface ClientService {

    /**
     * 查询用户
     * @return 用户名
     */
    @RequestMapping(value = "/selectUser",method = RequestMethod.GET)
    String selectUser();

}
