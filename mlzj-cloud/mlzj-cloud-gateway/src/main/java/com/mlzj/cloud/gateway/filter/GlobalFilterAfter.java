package com.mlzj.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author yhl
 * @date 2019/7/19
 */
@Component
public class GlobalFilterAfter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(exchange.getResponse().getStatusCode());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
