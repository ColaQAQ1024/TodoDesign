package com.todoDesign.mapper;

import com.todoDesign.dto.FinishQuest;
import com.todoDesign.entity.Quest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务 Mapper 接口
 * <p>
 * 这是一个用于操作任务的 Mapper 接口。
 * 通过继承 BaseMapper<Quest>，可以使用 MyBatis-Plus 提供的通用 CRUD 方法。
 *
 * @author Mory
 * @since 2023-08-10
 */
@Repository
public interface QuestMapper extends BaseMapper<Quest> {

    /**
     * 根据用户 ID 查询计划中的任务列表
     *
     * @param userId 用户 ID
     * @return 计划中的任务列表
     */
    List<FinishQuest> planning(int userId);

}
