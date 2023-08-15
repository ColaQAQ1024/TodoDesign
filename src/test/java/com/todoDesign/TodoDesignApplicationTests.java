package com.todoDesign;

import com.todoDesign.dto.QuestDTO;
import com.todoDesign.entity.Quest;
import org.junit.jupiter.api.Test;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoDesignApplicationTests {

    @Test
    void contextLoads() {
        ModelMapper modelMapper = new ModelMapper();
        //typeMap.addMappings(mapper -> mapper.skip(Destination::setName));
        TypeMap<Quest, QuestDTO> typeMap = modelMapper.createTypeMap(Quest.class,QuestDTO.class);
        typeMap.addMappings(mapping -> mapping.skip(Quest::getNameThing,QuestDTO::setNameThing));

        Converter<String, String> toUppercase =
                ctx -> ctx.getSource() == null ? null : ctx.getSource();
        typeMap.addMappings(mapper -> mapper.using(toUppercase).map(Quest::getNameThing,QuestDTO::setGroupName));

        Quest quest = new Quest();
        quest.setNameThing("halo!");
        QuestDTO questDTO = typeMap.map(quest);
        System.out.println(questDTO.getNameThing() + questDTO.getGroupName());
    }

}
