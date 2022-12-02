package com.mlzj.cloud.feign.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 标准化通用转发feign
 * @author yhl
 * @date 2022/11/30
 */
public interface DynamicFeignClient {

    @GetMapping("{path}?{param}")
    Object get(@PathVariable("path") String path, @PathVariable("param") String param);


    @PostMapping("{path}")
    Object post(@PathVariable("path") String path, @RequestBody Object obj);
}
