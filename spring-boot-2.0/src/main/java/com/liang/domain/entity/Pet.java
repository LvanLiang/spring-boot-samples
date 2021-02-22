package com.liang.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Liangxp
 * @date 2021/02/22 22:21
 */
@Data
public class Pet {
    private String name;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }
}
