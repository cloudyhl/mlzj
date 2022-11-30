package com.mlzj.cloud.feign.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 标准化通用转发feign
 * @author yhl
 * @date 2022/11/30
 */
public interface DynamicFeignClient {

    @GetMapping("{path}")
    Object get(@PathVariable("path") String path, @RequestParam String id);


    @PostMapping("{path}")
    Object post(@PathVariable("path") String path, @RequestBody Object obj);
}
