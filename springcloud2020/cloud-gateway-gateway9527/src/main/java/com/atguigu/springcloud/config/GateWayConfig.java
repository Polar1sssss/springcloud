package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hujtb
 * @Date: 2020/8/31 11:43
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator custonRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // 表示访问http://localhost:9527/guonei将会转发到http://news.baidu.com/guonei这个地址
        RouteLocator path_route_atguigu = routes.route("path_route_atguigu",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return path_route_atguigu;
    }
}
