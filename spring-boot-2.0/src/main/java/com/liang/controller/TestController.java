package com.liang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liangxp
 * @date 2021/2/24 14:58
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/he")
    public String hello(@RequestParam String name) {
        return "hello 你好：" + name;
    }
}
