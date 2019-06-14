package com.mlzj.cloud.feign.consul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * consul provider 服务提供方
 * @author yhl
 * @date 2019/6/12
 */
@FeignClient(name = "mlzj-consul-provider",path = "apiConsul/provider")
public interface ConsulProviderService {

    /**
     * 查询consul
     * @return 结果
     */
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    String selectConsul();

}
