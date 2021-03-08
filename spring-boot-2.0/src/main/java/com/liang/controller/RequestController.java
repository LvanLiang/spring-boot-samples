package com.liang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public Map success(@RequestAttribute("code") Integer code,
                       @RequestAttribute("message") String message) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", code);
        map.put("message", message);
        return map;
    }

}
