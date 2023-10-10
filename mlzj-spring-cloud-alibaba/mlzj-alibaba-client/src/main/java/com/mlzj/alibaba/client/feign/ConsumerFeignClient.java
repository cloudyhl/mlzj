package com.mlzj.alibaba.client.feign;

import com.mlzj.alibaba.client.dto.ClientReqDto;
import com.mlzj.alibaba.client.feign.fallback.ConsumerFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "alibaba-consumer", fallback = ConsumerFeignFallBack.class,contextId = "consumerFeignClient")
public interface ConsumerFeignClient {


    @GetMapping("/consumer/getConsumer")
    String getConsumer(@RequestParam String id);

    @PostMapping("/consumer/postConsumer")
    String postConsumer(@RequestBody ClientReqDto reqDto);

}
