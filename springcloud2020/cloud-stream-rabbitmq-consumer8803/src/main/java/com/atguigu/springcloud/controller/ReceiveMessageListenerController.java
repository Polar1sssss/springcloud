package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hujtb
 * @Date: 2020/9/1 16:28
 */
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 因为是消费者，所以返回值因该是void，否则启动不起来也不知道咋回事
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者二号, 接收到的消息是：" + message.getPayload() + "\t port:" + serverPort);
    }
}
