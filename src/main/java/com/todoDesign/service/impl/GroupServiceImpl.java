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

    //根据列表名获取对应的GroupId
    public int getGroupIdByGroupName(String groupName){
        return groupMapper.selectOne(new QueryWrapper<Group>().eq("group_id",groupName)).getGroupId();
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
