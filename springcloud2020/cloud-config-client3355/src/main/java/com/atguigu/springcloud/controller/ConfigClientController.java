package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hujtb
 * @Date: 2020/8/31 17:10
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String info;

    @GetMapping(value = "/configInfo")
    public String getInfo() {
        return info;
    }
}
