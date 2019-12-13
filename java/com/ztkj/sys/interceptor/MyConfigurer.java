package com.ztkj.sys.loginInterceptor;

import com.ztkj.sys.interceptor.LogInterceptor;
import com.ztkj.sys.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/26 19:09
 */
@Configuration
public class MyConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public LogInterceptor getLogInterceptor(){
        return new LogInterceptor();
    }

    /**
     * @description: 加入拦截器
     * @param: [registry]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 19:10
     */
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器注册
        InterceptorRegistration loginInterceptor = registry.addInterceptor(getLoginInterceptor());
        //设置拦截器的拦截路径 /** 表示拦截所有，包括html和css
        loginInterceptor.addPathPatterns("/**");
        //排除不用拦截的页面 ：登录页面和Controller的登录方法
        loginInterceptor.excludePathPatterns("/login/lo");
        loginInterceptor.excludePathPatterns("/login/ex");
        loginInterceptor.excludePathPatterns("/login.html");
//        loginInterceptor.excludePathPatterns("/left.html");
//        loginInterceptor.excludePathPatterns("/top.html");
//        loginInterceptor.excludePathPatterns("/main.html");
//        loginInterceptor.excludePathPatterns("/footer.html");
//        loginInterceptor.excludePathPatterns("/index.html");
        loginInterceptor.excludePathPatterns("/tools.html");
        loginInterceptor.excludePathPatterns("/myself.html");
        loginInterceptor.excludePathPatterns("/js/*");
        loginInterceptor.excludePathPatterns("/images/*");
        loginInterceptor.excludePathPatterns("/laydate/*");
        loginInterceptor.excludePathPatterns("/css/*");
        //日志拦截器注册
        InterceptorRegistration logInterceptor = registry.addInterceptor(getLogInterceptor());
//        System.out.println("===============================");
        logInterceptor.addPathPatterns("/sys/*/*");
        logInterceptor.addPathPatterns("/storage/*/*");
        logInterceptor.addPathPatterns("/purchase/*/*");
        logInterceptor.addPathPatterns("/market/*/*");
//        logInterceptor.excludePathPatterns("/sys/*/");

    }
}
