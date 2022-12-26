package com.lizi.boot.Controller;

import com.lizi.boot.Bean.User;
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

    @GetMapping("/getRequestHeader")
    public Map<String,Object> getRequestHeader(@RequestHeader("User-Agent") String userAgent,
                                               @RequestHeader Map<String,String> header){
        Map<String,Object> returnMap = new HashMap<>();

        returnMap.put("User-Agent",userAgent);
        returnMap.put("headers",header);
        return returnMap;
    }

    @GetMapping("/getRequestParam")
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

    @PostMapping(value = "/getRequestBody")
    public Map getRequestBody(@RequestBody String content){
        Map<String,Object> returnMap = new HashMap<>();

        returnMap.put("content",content);
        /*returnMap.put("UserName",user.getUserName());
        returnMap.put("PassWord",user.getPassWord());*/
        return returnMap;
    }

    /***
     * http://localhost:8080/cars/sell;low=22;brand=byd,audi,bm
     * http://localhost:8080/cars/sell;low=22;brand=byd;brand=audi;brand=bm
     * SpringBoot默认禁用矩阵变量的功能
     * 手动开启：urlPathHelper.setRemoveSemicolonContent(false);
     *      原理：urlPathHelper 路径解析时将分号后面内容移除了
     * 矩阵变量需要有URL路径变量才能被解析。
     * @param low
     * @param brand
     * @param path
     * @return
     */
    @GetMapping("/cars/{path}")
    public Map getMatrixVariableAndPathVariable(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();

        map.put("low：",low);
        map.put("brand：",brand);
        map.put("path：",path);
        return map;
    }

    /***
     * http://localhost:8080/getMatrixVariable/1;age=20/2;age=10
     *
     * @param bossAge
     * @param empAge
     * @return
     */
    @GetMapping("/getMatrixVariable/{bossId}/{empId}")
    public Map getMatrixVariable(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<>();

        map.put("bossAge：",bossAge);
        map.put("empAge：",empAge);
        return map;
    }
}
