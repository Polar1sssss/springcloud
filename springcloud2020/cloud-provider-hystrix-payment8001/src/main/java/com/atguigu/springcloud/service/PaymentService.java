package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hujtb
 * @Date: 2020/8/28 9:58
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "Thread Pool:" + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "\t" + "haha";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeOut = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Thread Pool:" + Thread.currentThread().getName() + "--id: " + id + "\t" + "wuwu";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "Thread Pool:" + Thread.currentThread().getName() + "--系统繁忙或运行报错, id: " + id + "\t" + "dodo";
    }

    // ==================服务提供端服务熔断====================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Handler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_Handler(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试";
    }
}
