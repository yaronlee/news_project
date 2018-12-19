package com.cskaoyan.springbootdemo.bean;

import java.util.Date;

public class News {

    String id;
    String image;
    String title;
    String link;
    Integer likeCount;
    Integer commentCount;
    Date createdDate;
    String userId;

    public News(){}

    public News(String id, String image, String title, String link, Integer likeCount, Integer commentCount, Date createdDate, String userId) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.link = link;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", createdDate=" + createdDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
