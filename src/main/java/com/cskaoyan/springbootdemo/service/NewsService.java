package com.cskaoyan.springbootdemo.service;

import com.cskaoyan.springbootdemo.bean.News;

import java.util.List;

public interface NewsService {

    List<News> findAllNews();
    Integer insert(News news);

}
