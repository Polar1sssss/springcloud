package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: hujtb
 * @Date: 2020/8/27 10:15
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/consul")
    public String paymentConsul() {
        return "springcloud with consul:" + port + UUID.randomUUID().toString();
    }
}
