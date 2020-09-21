package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.AccountDao;
import com.atguigu.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: hujtb
 * @Date: 2020/9/7 18:32
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decreaseAccount(Long userId, BigDecimal money) {
        log.info("-------->account-service中从账户中扣除余额开始");
        accountDao.decreaseAccount(userId, money);
        log.info("-------->account-service中从账户中扣除余额结束");
    }
}
