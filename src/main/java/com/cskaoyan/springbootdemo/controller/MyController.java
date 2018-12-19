package com.cskaoyan.springbootdemo.controller;


import com.cskaoyan.springbootdemo.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    //可以使用application.properties，也可以用yml格式

    //注意两个dest的区别
    @Value("${test.dest}")
    String dest;

    @Value("${test.from}")
    String from;


    @RequestMapping("/user")
    @ResponseBody
    public String doSomething(){
        return "hello world!" + "dest: " + dest + " form: " + from ;
    }

    //学习freemarker
    @RequestMapping("/free")
    @ResponseBody
    public String freeTest(Model model){
        return "this is a test";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }



}
