package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hujtb
 * @Date: 2020/8/25 16:19
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 向payment表插入数据
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入的记录为" + payment);
        if (result > 0) {
            return new CommonResult(200, "插入记录成功, 端口号：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "查询成功，端口号：" + serverPort, payment);
        } else {
            return new CommonResult(444, "未找到对应记录，id:" + id, null);
        }
    }

    /**
     * 从服务注册发现中心获取服务的详细信息，前提是服务主启动类要加@EnableDiscoveryClient注解
     * @return
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        // 注册到注册中心的所有服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("**********" + service);
        }
        // 具体某一服务的实例们
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("*********" + instance.getInstanceId() + "\t"
                    + instance.getHost() + "\t" + instance.getPort()) ;
        }

        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String testTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    /**
     * 测试zipkin
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi, im paymentzipkin server fall back~~~";
    }
}