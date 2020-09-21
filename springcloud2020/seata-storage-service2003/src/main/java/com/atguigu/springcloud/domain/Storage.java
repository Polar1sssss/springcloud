package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hujtb
 * @Date: 2020/9/9 15:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Long id;

    // 商品ID
    private Integer productId;

    // 总库存
    private Integer total;

    // 已用库存
    private Integer used;

    // 剩余库存
    private Integer residue;
}
