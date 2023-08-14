package com.todoDesign.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mory
 * &date  2023/8/14 9:28
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Configuration
public class ZModelMapperConfig {
    //类型转换
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
