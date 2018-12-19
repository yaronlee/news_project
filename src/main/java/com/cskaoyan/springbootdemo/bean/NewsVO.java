package com.cskaoyan.springbootdemo.bean;

public class NewsVO {

    User user;
    News news;
    Integer like;

    public NewsVO(){}

    public NewsVO(User user, News news, Integer like) {
        this.user = user;
        this.news = news;
        this.like = like;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
