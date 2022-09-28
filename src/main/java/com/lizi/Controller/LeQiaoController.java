package com.lizi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LeQiaoController {

    @RequestMapping("/test")
    @ResponseBody
    public String testOne(){
        return "spring boot 测试";
    }
}
