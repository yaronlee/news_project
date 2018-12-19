package com.cskaoyan.springbootdemo.dao;


import com.cskaoyan.springbootdemo.bean.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsDao {

    @Select("select * from news")
    List<News> findAllNews();


    @Insert("insert into news (id, title,image,likecount,commentcount,createddate,userid)" +
            "values (null,#{title},#{image},#{likeCount},#{commentCount},#{createdDate},#{userId})")
    Integer insert(News news);

}
