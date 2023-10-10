package com.mlzj.alibaba.client.feign.fallback;

import com.mlzj.alibaba.client.dto.ClientReqDto;
import com.mlzj.alibaba.client.feign.ConsumerFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2023/9/1
 */
@Component
public class ConsumerFeignFallBack implements ConsumerFeignClient {
    @Override
    public String getConsumer(String id) {
        return "getConsumerFallBack";
    }

    @Override
    public String postConsumer(ClientReqDto reqDto) {
        return "postConsumerFallBack";
    }
}
