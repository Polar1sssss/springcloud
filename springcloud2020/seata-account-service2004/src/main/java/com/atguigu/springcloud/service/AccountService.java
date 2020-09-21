package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 15:59
 */
public interface AccountService {
    /**
     * 扣减账户
     * @param userId
     * @param money
     */
    void decreaseAccount(Long userId, BigDecimal money);
}
