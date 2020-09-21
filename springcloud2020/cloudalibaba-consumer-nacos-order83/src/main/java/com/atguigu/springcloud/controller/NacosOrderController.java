package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: hujtb
 * @Date: 2020/9/2 14:53
 */
@RestController
@Slf4j
public class NacosOrderController {

    @Value("${service-url.nacos-user-service}")
    private String payment_url;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String getServerPort(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(payment_url + "/payment/nacos/" + id, String.class);
    }
}
