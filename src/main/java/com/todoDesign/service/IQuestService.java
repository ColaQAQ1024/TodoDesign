package com.todoDesign.service;

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

    ResponseEntity<String> add(Quest quest, int groupId);

}
