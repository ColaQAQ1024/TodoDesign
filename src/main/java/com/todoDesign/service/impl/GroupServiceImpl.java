package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.entity.Group;
import com.todoDesign.mapper.GroupMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.ITeammateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mory
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    private final ITeammateService iTeammateService;
    private final GroupMapper groupMapper;


    //通过列表名和userId获得对应的groupId
    @Override
    public int getGroupIdByGroupNameAndUserId(String groupName, int userId) {
        Integer groupId = groupMapper.getGroupIdByGroupNameAndUserId(groupName,userId);
        return (groupId != null) ? groupId : -1;
    }

    //创建新列表
    @Override
    public ResponseEntity<String> createGroup(Group group) {
        int userId = (Integer) StpUtil.getSession().get("userId");
        if (groupMapper.getGroupIdByGroupNameAndUserId(group.getGroupName(),userId) == null) {
            this.save(group);
            iTeammateService.saveByGroupIdAndUserId(group.getGroupId(),userId);
            return ResponseEntity.ok("列表：" + group.getGroupName() + " 创建成功");
        }else {
            return ResponseEntity.ok("列表：" + group.getGroupName() + " 已存在");
        }
    }
}
