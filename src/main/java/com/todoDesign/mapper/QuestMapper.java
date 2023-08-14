package com.todoDesign.mapper;

import com.todoDesign.dto.FinishQuest;
import com.todoDesign.entity.Quest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Repository
public interface QuestMapper extends BaseMapper<Quest> {

    List<FinishQuest> unFinishQuest(int groupId);

    List<FinishQuest> allFinish(int groupId);

    List<FinishQuest> planning(int userId);

    int finishThing(
            @Param("thing") String thing,
            int groupId
    );
}
