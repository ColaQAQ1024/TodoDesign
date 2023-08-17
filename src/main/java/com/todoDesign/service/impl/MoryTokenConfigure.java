package com.todoDesign.service.impl;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mory
 * &date  2023/8/16 14:17
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Configuration
public class MoryTokenConfigure implements WebMvcConfigurer {

    //设置 Sa - Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

}
