package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: hujtb
 * @Date: 2020/8/27 15:27
 */
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? current : current + 1;
        } while(!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*****next:" + next);
        return next;
    }

    @Override
    public ServiceInstance getInstances(List<ServiceInstance> service) {
        int index = getAndIncrement() % service.size();
        return service.get(index);
    }
}
