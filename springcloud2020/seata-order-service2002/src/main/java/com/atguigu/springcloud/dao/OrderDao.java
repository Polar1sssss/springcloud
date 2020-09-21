package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: hujtb
 * @Date: 2020/9/7 17:53
 */
@Mapper
public interface OrderDao {

    void createOrder(Order order);

    void updateStatus(@Param("userId") Long userId, @Param("status") Integer status);

}
