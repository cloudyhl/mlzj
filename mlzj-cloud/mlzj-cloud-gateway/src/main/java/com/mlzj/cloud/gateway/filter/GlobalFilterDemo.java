package com.mlzj.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * globalFilterDemo
 * @author yhl
 * @date 2019/6/17
 */
@Component
public class GlobalFilterDemo implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = exchange.getRequest().getQueryParams().getFirst("authToken");
        System.out.println(authToken);
        ServerHttpResponse response = exchange.getResponse();

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
