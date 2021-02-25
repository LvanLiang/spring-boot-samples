package com.liang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置绑定：
 *  1、自己的配置对象使用 @Component + @ConfigurationProperties
 *  2、第三方的没发加Component注解，所以使用@EnableConfigurationProperties + @ConfigurationPropertie
 * @author Liangxp
 * @date 2021/2/23 15:50
 */
@Data
@Component
@ConfigurationProperties(prefix = "car")
public class Car {
    private String brand;
    private Integer price;
}
