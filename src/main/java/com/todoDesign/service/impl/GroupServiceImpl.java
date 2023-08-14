package com.todoDesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.todoDesign.entity.Group;
import com.todoDesign.entity.GroupUser;
import com.todoDesign.mapper.GroupMapper;
import com.todoDesign.mapper.GroupUserMapper;
import com.todoDesign.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mory
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    private final GroupMapper groupMapper;
    private final GroupUserMapper groupUserMapper;

    @Autowired
    public GroupServiceImpl(GroupMapper groupMapper, GroupUserMapper groupUserMapper) {
        this.groupMapper = groupMapper;
        this.groupUserMapper = groupUserMapper;
    }

    //通过列表名和userId获得对应的groupId
    @Override
    public int getGroupIdByGroupNameAndUserId(String groupName, int userId) {
        Group group = groupMapper.selectOne(new QueryWrapper<Group>()
                .select("group_id")
                .inSql("group_id", "SELECT group_id FROM todo_user_group WHERE user_id = " + userId)
                .eq("group_name", groupName));
        return (group != null) ? group.getGroupId() : -1;
    }

    //更新group——user表的userId与groupId
    private void connectUserAndGroup(int userId, int groupId) {
        GroupUser groupUser = new GroupUser();
        groupUser.setUserId(userId);
        groupUser.setGroupId(groupId);
        groupUserMapper.insert(groupUser);
    }

    //创建新列表
    @Override
    public ResponseEntity<String> createGroup(Group group, int userId) {
        groupMapper.insert(group);
        connectUserAndGroup(userId, group.getGroupId());
        return ResponseEntity.ok(group.getGroupName() + "创建成功");
    }
}
