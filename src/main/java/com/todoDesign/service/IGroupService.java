package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.configure.Big;
import com.todoDesign.dto.GroupDTO;
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
     * 新建群组
     * @param group 群组信息
     * @return 响应实体
     */
    ResponseEntity<String> createGroup(Group group);

    Big<String> deleteGroup(GroupDTO groupDTO);

    Long incrementCounter();

    Integer getCounter();

    void cacheUser(User user);

    List<Object> getAllUserFromCache();
}
