package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.StorageDao;
import com.atguigu.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 17:36
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;

    @Override
    public void decreaseStorage(Long productId, Integer count) {
        storageDao.decreaseStorage(productId, count);
    }
}
