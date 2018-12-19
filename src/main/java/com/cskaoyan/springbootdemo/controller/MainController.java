package com.cskaoyan.springbootdemo.controller;

import com.cskaoyan.springbootdemo.bean.News;
import com.cskaoyan.springbootdemo.bean.NewsVO;
import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.service.NewsService;
import com.cskaoyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @RequestMapping(value={"/home","/"})
    public String firstController(Model model, HttpServletRequest request) {

        String contextPath = request.getContextPath();
        model.addAttribute("contextPath", contextPath);

        //VO中封装了news和相应的user
        List<NewsVO> vos = new ArrayList<>();

        List<News> newsList = newsService.findAllNews();
        for (News news:newsList) {
            String userId = news.getUserId();
            User user = userService.findUserById(userId);
            NewsVO newsVO = new NewsVO(user,news,news.getLikeCount());
            vos.add(newsVO);
        }

        model.addAttribute("vos", vos);
        return "home";
    }

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

}
