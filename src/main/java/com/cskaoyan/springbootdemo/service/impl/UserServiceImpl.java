package com.cskaoyan.springbootdemo.service.impl;

import com.cskaoyan.springbootdemo.bean.User;
import com.cskaoyan.springbootdemo.dao.UserDao;
import com.cskaoyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public Integer insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User login(String name, String password) {
        return userDao.findUserByNameAndPassword(name, password);
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }
}
