package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 17:34
 */
public interface StorageService {
    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    void decreaseStorage(Long productId, Integer count);
}
