package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.domain.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.springcloud.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 17:02
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decreaseAccount(@RequestParam("userId") Long userId,
                                        @RequestParam("money") BigDecimal money) {
        accountService.decreaseAccount(userId, money);
        return new CommonResult(200, "账户扣减成功");
    }
}
