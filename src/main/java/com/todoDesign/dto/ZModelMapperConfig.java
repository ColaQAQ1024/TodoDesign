package com.todoDesign.dto;

import com.todoDesign.entity.Quest;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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

    //Quest转换为QuestDTO
    public QuestDTO  exchangeQuestToQuestDTO (Quest quest){
        TypeMap<Quest, QuestDTO> typeMap = modelMapper().createTypeMap(Quest.class, QuestDTO.class);
        //装换器初始化
        Converter<String, String> exchangeSetting =
                ctx -> ctx.getSource() == null ? null : ctx.getSource();
        typeMap.addMappings(mapping -> mapping.using(exchangeSetting).map(Quest::getNameThing,QuestDTO::setGroupName))
                .addMappings(mapping -> mapping.skip(Quest::getNameThing,QuestDTO::setNameThing));
        return typeMap.map(quest);
    }

}
