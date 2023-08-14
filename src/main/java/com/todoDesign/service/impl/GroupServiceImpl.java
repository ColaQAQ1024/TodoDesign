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
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
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

    //根据列表名和用户Id获取对应的GroupId
    @Override
    public int getGroupIdByGroupNameAndUserId(String groupName,int userId){
        //*
        Group group = groupMapper.selectOne(new QueryWrapper<Group>()
                .select("todo_group.group_id")
                .inSql("todo_group.group_id", "SELECT group_id FROM todo_user_group WHERE user_id = " + userId)
                .eq("todo_group.group_name",groupName));
        //如果返回的group为null，则用户没有该昵称的对应表
        if (group != null){
            return group.getGroupId();
        }else{
            return -1;
        }
    }

    //连接对应GroupId和UserId
    private void connectUserAndGroup(int userId,int groupId){
        GroupUser groupUser = new GroupUser();
        groupUser.setUserId(userId);
        groupUser.setGroupId(groupId);
        groupUserMapper.insert(groupUser);
    }

    @Override
    public ResponseEntity<String> createGroup(Group group,int userId){
        groupMapper.insert(group);
        this.connectUserAndGroup(userId,group.getGroupId());
        return ResponseEntity.ok(group.getGroupName() + "创建成功");
    }

}
