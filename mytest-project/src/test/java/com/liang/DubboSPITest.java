package com.liang;

import com.liang.spi.Robot;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author Liangxp
 * @date 2020/10/14 11:46
 */
public class DubboSPITest {
    @Test
    public void sayHello() {
        ExtensionLoader<Robot> robotExtensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = robotExtensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = robotExtensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
