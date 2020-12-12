package com.liang.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author Liangxp
 * @date 2020/10/14 11:07
 */
@SPI
public interface Robot {
    void sayHello();
}
