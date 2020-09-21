package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: hujtb
 * @Date: 2020/8/25 17:51
 * 想要通过服务名去调用服务提供者，需要在RestTemplate配置上加@LoadBalanced注解
 * 否则它不知道该用哪种负载均衡的算法去调用服务提供者
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
