package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: hujtb
 * @Date: 2020/9/7 18:32
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * 下订单->减库存->减用户余额->改订单状态
     * @param order
     * name指的是全局事务名字，rollbackFor表示那些异常需要回滚
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void createOrder(Order order) {
        log.info("-------->开始创建订单");
        // 新建订单
        orderDao.createOrder(order);

        log.info("-------->订单开始调用库存微服务，做扣减");
        // 扣减库存
        storageService.decreaseStorage(order.getProductId(), order.getCount());
        log.info("-------->订单开始调用库存微服务，做扣减end");

        log.info("-------->订单开始调用账户微服务，做扣减");
        // 扣减账户余额
        accountService.decreaseAccount(order.getUserId(), order.getMoney());
        log.info("-------->订单开始调用账户微服务，做扣减end");

        log.info("修改订单状态开始");
        // 将订单状态由0改为1
        orderDao.updateStatus(order.getUserId(), 0);
        log.info("修改订单状态结束");
    }
}
