package com.ztkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//启动注解
@SpringBootApplication
//mybatis扫描dao层
@MapperScan("com.ztkj.*.dao")
//开启事物注解
@EnableTransactionManagement
//自动配置拦截器
@EnableAutoConfiguration
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }

}
