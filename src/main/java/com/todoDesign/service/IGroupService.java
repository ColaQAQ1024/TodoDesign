package com.todoDesign.service;

import com.todoDesign.entity.Group;
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
public interface IGroupService extends IService<Group> {

    int getGroupIdByGroupNameAndUserId(String groupName,int userId);

    /*
    新建列表
    */
    ResponseEntity<String> createGroup(Group group,int userId);


}
