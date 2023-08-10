package com.todoDesign;

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
    }

}
