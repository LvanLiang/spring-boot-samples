package com.liang.controller;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liangxp
 * @date 2020/6/24 16:46
 */
@Slf4j
@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/port")
    public String addWater() {
        log.info("时间:{}", DateUtil.now());
        return serverPort;
    }
}
