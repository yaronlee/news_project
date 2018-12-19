package com.cskaoyan.springbootdemo.controller;


import com.cskaoyan.springbootdemo.bean.News;
import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.service.NewsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @RequestMapping("/user/addNews")
    @ResponseBody
    public String addNews(HttpSession session,
                          @RequestParam("image") String image,
                          @RequestParam("title") String title,
                          @RequestParam("link") String link,
                          Model model){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String userId = user.getId();
            Integer likeCount = 0;
            Integer commentCount = 0;
            Date createdDate = new Date();

            News news = new News("0",image,title,link,
                    likeCount,commentCount,createdDate,userId);
            int update = newsService.insert(news);
        }

        return "redirect:/home";
    }

}
