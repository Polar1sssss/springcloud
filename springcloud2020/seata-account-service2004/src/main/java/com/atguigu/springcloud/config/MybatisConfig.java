package com.atguigu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 17:17
 */
@Configuration
@MapperScan({"com.atguigu.springcloud.dao"})
public class MybatisConfig {
}
