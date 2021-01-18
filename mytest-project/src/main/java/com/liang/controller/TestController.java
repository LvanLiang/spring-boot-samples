package com.liang.controller;

import cn.hutool.core.date.DateUtil;
import com.liang.config.MapConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Liangxp
 * @date 2020/6/24 16:46
 */
@Slf4j
@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private MapConfig mapConfig;

    @GetMapping("/port")
    public String addWater() {
        mapConfig.getMaps().forEach((k, v) -> {log.info("key:{}--value:{}",k, v);});
        log.info("时间:{}", DateUtil.now());
        return serverPort;
    }
}
