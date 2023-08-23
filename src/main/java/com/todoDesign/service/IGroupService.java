package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.entity.Group;
import com.todoDesign.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 群组服务接口
 * @author Mory
 */
public interface IGroupService extends IService<Group> {

    /**
     * 根据群组名和用户ID获取群组ID
     * @param groupName 群组名称
     * @param userId 用户ID
     * @return 群组ID
     */
    int getGroupIdByGroupNameAndUserId(String groupName, int userId);

    /**
     * 新建群组
     * @param group 群组信息
     * @return 响应实体
     */
    ResponseEntity<String> createGroup(Group group);

    Long incrementCounter();

    Integer getCounter();

    void cacheUser(User user);

    List<Object> getAllUserFromCache();
}
