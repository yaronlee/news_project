package com.cskaoyan.springbootdemo.controller;

import com.cskaoyan.springbootdemo.bean.News;
import com.cskaoyan.springbootdemo.bean.NewsVO;
import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.service.NewsService;
import com.cskaoyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


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



}
