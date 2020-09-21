package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: hujtb
 * @Date: 2020/8/27 18:00
 */
@RestController
@Slf4j
public class OrderFiegnController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        CommonResult<Payment> result = paymentFeignService.getPaymentById(id);
        return result;
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String testTimeOut() {
        return paymentFeignService.testTimeOut();
    }
}
