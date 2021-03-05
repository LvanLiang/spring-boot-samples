package com.liang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest风格支持（使用HTTP请求方式动词来表示对资源的操作）
 * 以前：/getUser   获取用户     /deleteUser 删除用户    /editUser  修改用户       /saveUser 保存用户
 * 现在：/user    GET-获取用户    DELETE-删除用户     PUT-修改用户      POST-保存用户
 * • 核心Filter；HiddenHttpMethodFilter
 *      • 用法： 表单method=post，隐藏域 _method=put
 *      • SpringBoot中手动开启
 * @author Liangxp
 * @date 2021/3/2 17:18
 */
@Controller
@ResponseBody
public class RestController {

    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getParams(@PathVariable("id") Integer id,
                                         @PathVariable("username") String username,
                                         @PathVariable Map paraMap,
                                         @RequestHeader Map<String, String> headerMap,
                                         @RequestParam("age") Integer age) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("pathvariableParam", paraMap);
        map.put("headerMap", headerMap);
        return map;

    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUser(){
        return "GET-张三";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveUser(){
        return "POST-张三";
    }


    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String putUser(){
        return "PUT-张三";
    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public String deleteUser(){
        return "DELETE-张三";
    }
}
