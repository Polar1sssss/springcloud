package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hujtb
 * @Date: 2020/9/7 17:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    /**
     * 两个参数的构造方法
     */
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
