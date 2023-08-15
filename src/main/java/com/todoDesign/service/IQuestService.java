package com.todoDesign.service;

import com.todoDesign.dto.QuestDTO;
import com.todoDesign.entity.Quest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

/**
 * 任务服务接口
 * @author Mory
 */
public interface IQuestService extends IService<Quest> {

    /**
     * 根据任务名称和群组ID获取任务ID
     * @param nameThing 任务名称
     * @param groupId 群组ID
     * @return 任务ID
     */
    int getQuestIdByNameThingAndGroupId(String nameThing, int groupId);

    /**
     * 添加新任务
     * @param questDTO 任务信息DTO
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<String> add(QuestDTO questDTO, int userId);

    /**
     * 完成所有同名任务
     * @param groupName 群组名称
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<Object> allFinish(String groupName, int userId);

    /**
     * 未完成所有同名任务
     * @param groupName 群组名称
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<Object> unFinish(String groupName,int userId);

    /**
     * 计划任务
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<Object> planning(int userId);

    /**
     * 完成任务
     * @param questDTO 任务信息DTO
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<String> finish(QuestDTO questDTO, int userId);

    /**
     * 删除任务
     * @param questDTO 任务信息DTO
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<String> deleteThing(QuestDTO questDTO, int userId);

    /**
     * 设置任务为星标任务
     * @param questDTO 任务信息DTO
     * @param userId 用户ID
     * @return 响应实体
     */
    ResponseEntity<String> setStar(QuestDTO questDTO, int userId);

}
