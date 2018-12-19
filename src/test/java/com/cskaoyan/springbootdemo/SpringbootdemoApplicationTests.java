package com.cskaoyan.springbootdemo;

import com.cskaoyan.springbootdemo.bean.News;
import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.dao.NewsDao;
import com.cskaoyan.springbootdemo.dao.UserDao;
import com.cskaoyan.springbootdemo.service.NewsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootdemoApplication.class)
@WebAppConfiguration
public class SpringbootdemoApplicationTests {

    @Autowired
    NewsService newsService;

    @Autowired
    UserDao userDao;

    @Autowired
    NewsDao newsDao;

    @Test
    public void testFindUserByName() {

        User user = userDao.findUserByName("yaron");
        System.out.println("user = " + user);

        Assert.assertNotNull(user);

    }

    @Test
    public void testFindUserByNameAndPassword() {
        User user = userDao.findUserByNameAndPassword("yaron", "123456");
        System.out.println("user = " + user);

        Assert.assertNotNull(user);
    }

    @Test
    public void testFindAllUsers(){
        List<User> users = userDao.findAllUsers();
        System.out.println("users = " + users);

    }

    @Test
    public void testFindAllNews(){
        List<News> newsList = newsDao.findAllNews();
        System.out.println("news = " + newsList);
    }


    /**
     * 为什么会出空指针异常？
     */
    @Test
    public void insertNewsTest(){
        String image = "test";
        String title = "test";
        String link = "test";
        Integer likeCount = 0;
        Integer commentCount = 0;
        Date createdDate = new Date();

        News news = new News("0",image,title,link,
                likeCount,commentCount,createdDate,"0");

        System.out.println("newsService = " + newsService);
        int insert = newsService.insert(news);
        System.out.println("insert = " + insert);
    }

}

