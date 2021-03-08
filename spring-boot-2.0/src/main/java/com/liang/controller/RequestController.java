package com.liang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Liangxp
 * @date 2021/03/08 21:24
 */
@Controller
public class RequestController {

    @GetMapping("/forward")
    public String forward(HttpServletRequest request) {
        request.setAttribute("code", 200);
        request.setAttribute("message", "成功了！！！");
        return "forward:/success";
    }

    @GetMapping("/success")
    @ResponseBody
    public Map success(@RequestAttribute(value = "code", required = false) Integer code,
                       @RequestAttribute(value = "message", required = false) String message,
                       HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", code);
        map.put("message", message);
        // 转发request的参数
        map.put("requestMap", request.getAttribute("map"));
        map.put("requestModel", request.getAttribute("model"));
        map.put("requestMsg", request.getAttribute("requestMsg"));
        return map;
    }

    @GetMapping("/param")
    public String getParam(Map<String, Object> map, Model model,
                           HttpServletRequest request, HttpServletResponse response) {
        map.put("map", "hello");
        model.addAttribute("model", "world");
        request.setAttribute("requestMsg", "hi!");
        Cookie cookie = new Cookie("key", "258");
        response.addCookie(cookie);
        return "forward:/success";
    }

}
