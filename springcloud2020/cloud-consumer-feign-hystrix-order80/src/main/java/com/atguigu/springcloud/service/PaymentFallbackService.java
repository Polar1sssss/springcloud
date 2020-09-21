package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: hujtb
 * @Date: 2020/8/28 16:06
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK服务降级。。。";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut服务降级。。。";
    }
}
