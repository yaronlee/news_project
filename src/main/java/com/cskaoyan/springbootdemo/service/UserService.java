package com.cskaoyan.springbootdemo.service;

import com.cskaoyan.springbootdemo.bean.User;

import java.util.List;

public interface UserService {

    User login(String name, String password);

    User findUserById(String id);

    User findUserByName(String name);

    List<User> findAllUsers();

    Integer insert(User user);

}
