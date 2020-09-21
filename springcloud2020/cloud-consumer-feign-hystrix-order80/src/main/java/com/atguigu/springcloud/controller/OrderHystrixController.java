package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: hujtb
 * @Date: 2020/8/28 11:16
 * 消费端自定义的服务降级
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallback") // 全局服务降级方法
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService orderHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = orderHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10/0;
        String result = orderHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

//    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id) {
//
//        return "我们是消费者80，支付端响应超时或出现异常。。。";
//    }

    // 全局服务降级兜底方法
    public String paymentGlobalFallback() {
        return "Global异常处理信息，请稍后再试。。。";
    }
}
