package com.mlzj.cloud.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * globalFilterDemo
 *
 * @author yhl
 * @date 2019/6/17
 */
@Component
@Slf4j
public class GlobalFilterDemo implements GlobalFilter, Ordered {


    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = exchange.getRequest().getQueryParams().getFirst("authToken");
        exchange.getAttributes().put("countStartTime", System.currentTimeMillis());
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        //newRequest增加新的请求头
        ServerHttpRequest newRequest = request.mutate().header("prefix", "xxxx").build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        /*
        //修改返回
        serverHttpResponse.setStatusCode(HttpStatus.BAD_GATEWAY);
        byte[] bytes = "{\"status\":\"-1\",\"msg\":\"error\"}".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
        */
        return chain.filter(newExchange).then(Mono.fromRunnable(() -> {
            log.info("time+{}", (Object) exchange.getAttribute("countStartTime"));
            log.info("time use{}", System.currentTimeMillis() - (long) exchange.getAttribute("countStartTime"));
            ServerHttpResponse response = exchange.getResponse();
            System.out.println(response.getStatusCode().value());
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
