package com.todoDesign;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mory
 */
@SpringBootApplication
@MapperScan("com.todoDesign.mapper")
public class TodoDesignApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoDesignApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
