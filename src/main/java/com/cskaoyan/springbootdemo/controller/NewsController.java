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
import java.util.HashMap;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @RequestMapping("/user/addNews")
    @ResponseBody
    public HashMap addNews(HttpSession session,
                          News news){

        HashMap map = new HashMap();

        User user = (User) session.getAttribute("user");
        if (user != null) {
            news.setCommentCount(0);
            news.setLikeCount(0);
            news.setCreatedDate(new Date());
            String userId = user.getId();
            news.setUserId(userId);
            int update = newsService.insert(news);
            map.put("code",0);
        } else {
            map.put("code",1);
            map.put("msg","something went wrong");
        }
        return map;
    }

}
