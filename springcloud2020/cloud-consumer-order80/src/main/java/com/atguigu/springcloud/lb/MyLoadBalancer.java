package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: hujtb
 * @Date: 2020/8/27 15:20
 */
public interface MyLoadBalancer {
    ServiceInstance getInstances(List<ServiceInstance> serviceInstances);
}
