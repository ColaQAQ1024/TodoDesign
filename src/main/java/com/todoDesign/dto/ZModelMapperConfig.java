package com.todoDesign.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

    public <T, R> List<R> convertList(List<T> sourceList, Class<R> targetClass) {
        List<R> resultList = new ArrayList<>();
        for (T source : sourceList) {
            R target = this.modelMapper().map(source, targetClass);
            resultList.add(target);
        }
        return resultList;
    }

}
