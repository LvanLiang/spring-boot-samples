package com.liang.spi.impl;

import com.liang.spi.Robot;

/**
 * @author Liangxp
 * @date 2020/10/14 11:08
 */
public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
