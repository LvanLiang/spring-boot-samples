package com.liang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liangxp
 * @date 2020/6/24 16:46
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/port")
    public String addWater() {
        return serverPort;
    }
}
