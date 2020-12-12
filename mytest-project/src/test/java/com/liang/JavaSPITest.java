package com.liang;

import com.liang.spi.Robot;
import com.liang.spi.impl.OptimusPrime;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author Liangxp
 * @date 2020/10/14 11:26
 */
public class JavaSPITest {
    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

    @Test
    public void testRefleact() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<OptimusPrime> aClass = (Class<OptimusPrime>) Class.forName("com.liang.spi.impl.OptimusPrime");
        OptimusPrime optimusPrime = aClass.newInstance();
        optimusPrime.sayHello();
    }
}
