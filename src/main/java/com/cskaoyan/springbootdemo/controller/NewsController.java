package com.cskaoyan.springbootdemo.controller;


import com.cskaoyan.springbootdemo.bean.Comment;
import com.cskaoyan.springbootdemo.bean.News;
import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.bean.vo.CommentVO;
import com.cskaoyan.springbootdemo.service.CommentService;
import com.cskaoyan.springbootdemo.service.NewsService;
import com.cskaoyan.springbootdemo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    UserService userService;
    @Autowired
    NewsService newsService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/user/addNews")
    @ResponseBody
    public HashMap addNews(HttpSession session, News news){
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

    @RequestMapping("/news/{newsId}")
    public String newsDetail(@PathVariable String newsId,
                             Model model){

        //1，根据newsId找到对应的news，根据news找到对应的owner
        //2，根据newsId找到所有的comment，然后每个comment都设置一个vo
        System.out.println("newsId = " + newsId);

        //根据newsId找到对应的news
        News news = newsService.findNewsById(newsId);
        //根据newsId找到所有的comments
        List<Comment> commentList = commentService.selectCommentsByNewsId(newsId);
        //创建comments，即List<CommentVO>
        List<CommentVO> comments = new ArrayList<>();

        String ownerId = news.getUserId();
        User owner = userService.findUserById(ownerId);

        for (Comment comment : commentList){
            CommentVO commentVO = new CommentVO();
            commentVO.setComment(comment);
            commentVO.setUser(owner);
            comments.add(commentVO);
        }

        //owner是当前信息的发布者，而user是当前的登录者
        model.addAttribute("owner", owner);
        model.addAttribute("news", news);
        model.addAttribute("comments", comments);
        model.addAttribute("contextPath","");
        //暂时性
        model.addAttribute("like",1);
        return "detail";
    }

    @RequestMapping("/addComment")
    public String addComment(Comment comment, Model model){

        comment.setCreatedDate(new Date());
        commentService.insert(comment);

        return "redirect:/news/" + comment.getNewsId();

    }

}
