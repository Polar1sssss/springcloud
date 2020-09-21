package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Author: hujtb
 * @Date: 2020/9/4 16:05
 */
@Component
public class PaymentFallbackService implements PaymentService{
    /**
     * 兜底方法
     * @param id
     * @return
     */
    @Override
    public CommonResult<Payment> paymentSql(Long id) {
        return new CommonResult<>(4442, "openfeign调用服务的全局兜底方法触发了~~");
    }
}
