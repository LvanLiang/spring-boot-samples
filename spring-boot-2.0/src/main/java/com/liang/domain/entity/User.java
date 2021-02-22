package com.liang.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Liangxp
 * @date 2021/02/22 22:20
 */
@Data
@AllArgsConstructor
public class User {
    private String name;

    private Integer age;

    private Pet pet;
}
