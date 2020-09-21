package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * @Author: hujtb
 * @Date: 2020/9/7 17:53
 */
@Mapper
public interface AccountDao {

    void decreaseAccount(@Param("userId") Long userId, @Param("money") BigDecimal money);

}
