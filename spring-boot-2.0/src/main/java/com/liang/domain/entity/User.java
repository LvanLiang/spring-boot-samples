package com.liang.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Liangxp
 * @date 2021/02/22 22:20
 */
@Data
public class User {
    private String name;

    private Integer age;

    private Pet pet;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }
}
