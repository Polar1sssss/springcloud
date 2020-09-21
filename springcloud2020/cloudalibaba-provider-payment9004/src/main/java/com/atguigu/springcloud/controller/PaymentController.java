package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: hujtb
 * @Date: 2020/9/4 11:09
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    public static HashMap<Long, Payment> hashmap = new HashMap<>();
    static {
        hashmap.put(2020L, new Payment(2020L, "serial001"));
        hashmap.put(2021L, new Payment(2021L, "serial001"));
        hashmap.put(2022L, new Payment(2022L, "serial001"));
    }

    @GetMapping("/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        CommonResult<Payment> result = new CommonResult<>(200, "from port:" + serverPort, hashmap.get(id));
        return result;
    }
}
