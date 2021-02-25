package com.liang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Liangxp
 * @date 2021/02/25 21:38
 */
@Data
@ConfigurationProperties(prefix = "mycar")
public class MyCar {
    private String brand;
    private String color;
}
