package com.liang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author Liangxp
 * @date 2021/1/18 14:58
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "site")
public class MapConfig {
    private Map<String,String> maps;
}
