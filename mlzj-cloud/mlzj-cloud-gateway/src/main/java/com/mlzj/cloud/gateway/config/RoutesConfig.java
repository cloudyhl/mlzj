package com.mlzj.cloud.gateway.config;

import com.mlzj.cloud.gateway.filter.CustomGateWayFilter;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 路由配置
 * @author yhl
 * @date 2019/6/14
 */
@Configuration
public class RoutesConfig {

//    @Bean
//    public RouteLocator locator(RouteLocatorBuilder builder){
//        return builder.routes().route("jd_routes",route-> route.path("/jd").uri("http://jd.com/80")).build();
//    }

    /**
     * 为过滤出的请求增加header
     * @param builder 构建器
     * @return 路由
     */
/*    @Bean
    public RouteLocator locator(RouteLocatorBuilder builder){
        return builder.routes().route("jd_routes",route-> route.path("/mlzj-client/**").filters(
                gatewayFilterSpec -> gatewayFilterSpec.addRequestHeader("prefix","header")
                        //增加请求参数
                        .addRequestParameter("username","password").stripPrefix(2)
                        //设置重试
                        .retry(retryConfig -> retryConfig.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR))
                        //开启网关hystrix
                        .hystrix(config -> config.setFallbackUri("forward:/failBack").setName("hystrix"))
        ).uri("http://localhost:20003/addUser")).build();
    }*/

    /**
     * gatewayFilter 需要使用该方式进行配置
     * @param builder builder
     * @return 路由分发器
     */

    public RouteLocator locator(RouteLocatorBuilder builder){
        return builder.routes().route("mlzj-client",route ->
          route.path("/mlzj-client/**").filters(gatewayFilterSpec -> gatewayFilterSpec.filter(new CustomGateWayFilter()))
                .uri("http://localhost:20003/")
        ).build();

    }


}
