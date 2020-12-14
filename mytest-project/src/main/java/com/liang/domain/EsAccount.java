package com.liang.domain;

import lombok.Data;

/**
 * @author Liangxp
 * @date 2020/12/14 15:12
 */
@Data
public class EsAccount {
    private int accountNumber;
    private int balance;
    private String firstname;
    private String lastname;
    private int age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
