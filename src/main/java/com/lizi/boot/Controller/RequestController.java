package com.lizi.boot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/getRequestAttribute")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","报错！！");
        request.setAttribute("code",200);
        // 转发请求
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute String msg,
                       @RequestAttribute Integer code,
                       HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("code",code);
        map.put("Request(msg)",request.getAttribute("msg"));
        map.put("Request(code)",request.getAttribute("code"));
        return map;
    }
}
