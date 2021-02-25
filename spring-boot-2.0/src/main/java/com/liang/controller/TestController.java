package com.liang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Liangxp
 * @date 2021/2/24 14:58
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("#{'${name.list}'}")
    private List<String> nameList;


    @Value("#{${nameme.map}}")
    private Map<String, Object> map;


    @GetMapping("/he")
    public String hello(@RequestParam String name) {
        return "hello 你好：" + name + nameList + map;
    }
}
