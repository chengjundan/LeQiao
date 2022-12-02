package com.lizi.boot.Controller;

import com.lizi.boot.Bean.Car;
import com.lizi.boot.Bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }

    // @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser(){
        return "POST-张三";
    }

    // @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        return "PUT-张三";
    }

    // @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-张三";
    }

    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    @RequestMapping("/show")
    public String index(Model model){
        model.addAttribute("msg","一给Giao");
        return "index";
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
