package com.liang.controller;

import com.liang.domain.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 自定义类型参数 封装POJO[ServletModelAttributeMethodProcessor]
     * WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
     * WebDataBinder :web数据绑定器，将请求参数的值绑定到指定的JavaBean里面
     * WebDataBinder 利用它里面的 Converters 将请求数据转成指定的数据类型。再次封装到JavaBean中
     * GenericConversionService：在设置每一个值的时候，找它里面的所有converter那个可以将这个数据类型（request带来参数的字符串）转换到指定的类型（JavaBean -- Integer）
     * @param person
     * @return
     */
    @PostMapping("/savePerson")
    public Person savePerson(Person person) {
        return person;
    }
}
