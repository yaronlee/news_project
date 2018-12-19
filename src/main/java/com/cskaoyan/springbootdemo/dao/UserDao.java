package com.cskaoyan.springbootdemo.dao;


import com.cskaoyan.springbootdemo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//以前需要在xml文件中配置，现在只需要用注解就可以了
@Mapper
public interface UserDao {

    @Select("select * from user")
    List<User> findAllUsers();

    @Select("select * from user where id = #{id}")
    User findUserById(String id);

    @Select("select * from user where name = #{name}")
    User findUserByName(String username);

    //在resources中的xml文件中配置
    //@Select("select * from user where username = #{username} and password = #{password}")
    User findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Insert("insert into user (id, name, password) values (null, #{name}, #{password})")
    Integer insert(User user);

}
