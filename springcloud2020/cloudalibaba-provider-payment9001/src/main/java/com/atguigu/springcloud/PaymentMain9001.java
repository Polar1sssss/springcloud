package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: hujtb
 * @Date: 2020/9/2 11:47
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9001.class, args);
    }

//    @RestController
//    public class EchoController{
//        @GetMapping(value = "/echo/{string}")
//        public String echo(@PathVariable String string) {
//            return "Hello Nacos Discovery " + string;
//        }
//    }
}
