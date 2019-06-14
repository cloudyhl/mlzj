package com.mlzj.cloud.feign.service.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试feignService
 * @author yhl
 * @date 2019/6/2
 */
@FeignClient(name = "github-client",url = "https://api.github.com")
public interface DemoService {
    /**
     * 查询git仓库demo
     * @param query 查询条件
     * @return 查询结果
     */
    @RequestMapping(value = "/search/repositories",method = RequestMethod.GET)
    String queryGitHub(@RequestParam("q") String query);
}
