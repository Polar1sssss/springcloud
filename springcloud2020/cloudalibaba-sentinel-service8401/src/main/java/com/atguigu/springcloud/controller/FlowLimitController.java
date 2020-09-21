package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hujtb
 * @Date: 2020/9/3 15:31
 */
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping(value = "/testA")
    public String testA() {
        log.info(Thread.currentThread().getName() + "\t" + "testA");
        return "-----------testA";
    }

    @GetMapping(value = "/testB")
    public String testB() {
        return "-----------testB";
    }

    /**
     * sentinel降级至RT：平均响应时间超过阈值，并且请求数大于5，触发熔断，进入时间窗口间隔时长的熔断器
     * 超过时间窗口时长，熔断器关闭
     *
     * @return
     */
    @GetMapping(value = "/testD")
    public String testD() {
        int age = 10 / 0;
        //log.info("---------testD, 测试RT");
        log.info("---------testD, 测试异常比例");
        return "-----------testD";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        int age = 10/0;
        return "------------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "------------deal_testHotKey";
    }
}
