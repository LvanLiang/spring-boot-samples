package com.liang.domain;

import lombok.Data;

/**
 * @author Liangxp
 * @date 2020/4/22 16:58
 */
@Data
public class TbOrder {
    private Long id;

    private Long orderId;

    private Long userId;

    private String name;
}