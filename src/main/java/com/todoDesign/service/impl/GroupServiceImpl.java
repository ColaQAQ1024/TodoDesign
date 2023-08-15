package com.todoDesign.service.impl;

import com.todoDesign.entity.Group;
import com.todoDesign.mapper.GroupMapper;
import com.todoDesign.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mory
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    //通过列表名和userId获得对应的groupId
    @Override
    public int getGroupIdByGroupNameAndUserId(String groupName, int userId) {
        Integer groupId = this.query()
                .eq("group_name",groupName)
                .eq("user_id",userId)
                .one().getGroupId();
        return (groupId != null) ? groupId : -1;
    }

    //创建新列表
    @Override
    public ResponseEntity<String> createGroup(Group group) {
        this.save(group);
        return ResponseEntity.ok(group.getGroupName() + "创建成功");
    }
}
