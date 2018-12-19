package com.cskaoyan.springbootdemo.controller;


import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 如果用户名或密码不正确：{"code":1,"msgpwd":"密码不正确"}
     * 如果密码正确，就把code设置为0，并把查询到的user放到session中
     *
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map loginController(HttpSession session,
                               @RequestParam("username") String name,
                               @RequestParam("password") String password){

        Map<String, Object> map = new HashMap<>();

        User user = userService.login(name, password);

        if (user!=null) {
            map.put("code",0);
            session.setAttribute("user",user);
        } else {
            map.put("code",1);
            map.put("msgpwd","密码不正确");
        }
        return map;
    }

    @RequestMapping("/logout")
    public String logoutController(HttpSession session) {
        if (session!=null){
            session.invalidate();
        }
        return "redirect:/home";
    }


    @RequestMapping("/reg")
    @ResponseBody
    public HashMap register(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        HashMap map = new HashMap();

        User user = new User();
        user.setName(username);
        user.setPassword(password);

        User userByName = userService.findUserByName(username);

        if (userByName == null) {
            userService.insert(user);
            //用户注册后，直接实现登录效果
            session.setAttribute("user",user);
            map.put("code",0);
        } else{
            map.put("code",1);
            map.put("msgname","用户名已存在");
        }
        return map;

    }

}
