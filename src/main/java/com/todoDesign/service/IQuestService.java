package com.todoDesign.service;

import com.todoDesign.dto.QuestDTO;
import com.todoDesign.entity.Quest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
public interface IQuestService extends IService<Quest> {

    ResponseEntity<String> add(QuestDTO questDTO, int userId);

    ResponseEntity<Object> allFinish(String groupName,int userId);

    ResponseEntity<Object> planing(int userId);

    ResponseEntity<String> finish(QuestDTO questDTO,int groupId);

}
