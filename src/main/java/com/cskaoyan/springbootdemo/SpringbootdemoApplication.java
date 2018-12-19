package com.cskaoyan.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//刚导入mysql包还没配置的时候可以加上exclude，配置完之后就不需要加了
@SpringBootApplication
//如果要开启事务的话，必须在springboot启动类里开启事务
//使用的时候直接在service上面加上@Transactional
@EnableTransactionManagement
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

}

