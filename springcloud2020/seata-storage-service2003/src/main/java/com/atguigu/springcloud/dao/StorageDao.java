package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 15:55
 */
@Mapper
public interface StorageDao {
    void decreaseStorage(@Param("productId") Long productId, @Param("count") Integer count);
}
