package com.cskaoyan.springbootdemo.service.impl;

import com.cskaoyan.springbootdemo.bean.News;
import com.cskaoyan.springbootdemo.dao.NewsDao;
import com.cskaoyan.springbootdemo.service.NewsService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDao newsDao;

    @Override
    public List<News> findAllNews() {
        return newsDao.findAllNews();
    }

    @Override
    public Integer insert(News news) {
        return newsDao.insert(news);
    }
}
