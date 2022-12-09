package com.lizi.boot.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    @GetMapping("/path/{variableInt}/owner/{variableStr}")
    public Map<String,Object> getPathVariable(@PathVariable("variableInt") Integer variable,
                                              @PathVariable("variableStr")String name,
                                              @PathVariable Map<String,String> map){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("variableInt",variable);
        returnMap.put("variableStr",name);
        returnMap.put("pv",map);
        return returnMap;
    }

    @GetMapping("/req")
    public Map<String,Object> getRequestHeader(@RequestHeader("User-Agent") String userAgent,
                                               @RequestHeader Map<String,String> header){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("User-Agent",userAgent);
        returnMap.put("headers",header);
        return returnMap;
    }

    @GetMapping("/reqParam")
    public Map<String,Object> getRequestParam(@RequestParam("age") String age,
                                               @RequestParam("inters") List<String> inters,
                                               @RequestParam Map<String,String> params){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("age",age);
        returnMap.put("inters",inters);
        returnMap.put("params",params);
        return returnMap;
    }

    @GetMapping("/getCookieValue")
    public Map<String,Object> getCookieValue(@CookieValue("_ga") String _ga,
                                             @CookieValue("_ga") Cookie cookie){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("_ga",_ga);
        returnMap.put("cookieName",cookie.getName());
        returnMap.put("cookieValue",cookie.getValue());
        return returnMap;
    }

    @GetMapping("/getRequestBody")
    public Map<String,Object> getRequestBody(@RequestBody String content){
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("content",content);
        return returnMap;
    }
}
