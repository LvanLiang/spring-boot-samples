package com.liang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
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
@Slf4j
@Controller
@ResponseBody
public class RestController {

    /**
     * @RequestParam
     *      用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容。
     *      （Http协议中，如果不指定Content-Type，则默认传递的参数就是application/x-www-form-urlencoded类型）
     *      RequestParam可以接受简单类型的属性，也可以接受对象类型。
     *
     * @RequestBody
     *      处理HttpEntity传递过来的数据，一般用来处理非Content-Type: application/x-www-form-urlencoded编码格式的数据。
     *      GET请求中，因为没有HttpEntity，所以@RequestBody并不适用。
     *      POST请求中，通过HttpEntity传递的参数，必须要在请求头中声明数据的类型Content-Type，
     *      SpringMVC通过使用HandlerAdapter 配置的HttpMessageConverters来解析HttpEntity中的数据，然后绑定到相应的bean上。
     * @return
     */
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getParams(@PathVariable("id") Integer id,
                                         @PathVariable("username") String username,
                                         @PathVariable Map paraMap,
                                         @RequestHeader Map<String, String> headerMap,
                                         @RequestParam("age") Integer age,
                                         @RequestParam("inters") List<String> interestList,
                                         @RequestParam Map<String, Object> paramMap,
                                         @CookieValue("_ga") String ga,
                                         @CookieValue("_ga") Cookie cookie) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("id", id);
        map.put("username", username);
        map.put("pathVariableParam", paraMap);
        map.put("headerMap", headerMap);
        map.put("age", age);
        map.put("interestList", interestList);
        map.put("paramMap", paramMap);
        log.info("cookie名称：；cookie值：", cookie.getName(), cookie.getValue());
        return map;

    }

    /**
     * 矩阵变量
     * @return
     */
    @GetMapping("/car/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brandList,
                        @PathVariable("path") String path){
        Map<String, Object> map = new HashMap<>(8);
        map.put("low", low);
        map.put("brandList", brandList);
        map.put("path", path);
        return map;
    }

    @GetMapping("/boss/{bossId}/{empId}")
    public Map bossAge(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                       @MatrixVariable(value = "age",pathVar = "empId") Integer empAge,
                       @PathVariable("bossId") Integer bossId,
                       @PathVariable("empId") Integer empId){
        Map<String, Object> map = new HashMap<>(8);
        map.put("bossId", bossId);
        map.put("bossAge", bossAge);
        map.put("empId", empId);
        map.put("empAge", empAge);
        return map;
    }

    @PostMapping("/save")
    public Map save(@RequestBody String contentMap) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("contentMap", contentMap);
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
