package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: hujtb
 * @Date: 2020/9/4 11:37
 */
@RestController
@Slf4j
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    private String prividerUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    /**
     * fallback属性提供java程序异常后的降级方法， blockHandler提供违反sentinel配置中心规则的降级方法
     * @param id
     * @return
     */
    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback", fallback = "handlerFallBack")
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallBack",
            blockHandler = "blockHandler", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallBack(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(prividerUrl + "/paymentSql/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该ID没有对应记录, 空指针异常");
        }
        return result;
    }

    public CommonResult<Payment> handlerFallBack(@PathVariable("id") Long id, Throwable e) {
        return new CommonResult<>(4444, "fallback降级方法--异常" + e.getMessage());
    }

    /**
     *
     * @param id
     * @param e 违反sentinel监控台配置规则后抛出的异常，参数里面必须写这个才能进行自定义的降级处理
     * @return
     */
    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException e) {
        return new CommonResult<>(4444, "blockHandler降级方法--异常" + e.getMessage());
    }

    // ===================openfeign=================
    @GetMapping("/consumer/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {
        return paymentService.paymentSql(id);
    }
}
