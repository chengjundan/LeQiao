package com.lizi.boot.Controller;

import com.lizi.boot.Bean.Car;
import com.lizi.boot.Bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*@ResponseBody
@Controller*/
@Slf4j
@RestController
@RequestMapping("/LeQiao")
public class LeQiaoController {
    @Autowired
    private Car car;

    @Autowired
    private Person person;

    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    @RequestMapping("/person")
    public Person person(){
        return person;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String testOne(){
        // printVariable("1","2","3");
        // printVariable("a","b");
        return "spring boot 测试";
    }

    public static void printVariable(String... args){
        for (String str:args) {
            System.out.println(str);
        }
    }
    public static void printVariable(String str,String str2){

        System.out.println(str+str2);
    }

    public static void main(String[] args) {

    }
}
