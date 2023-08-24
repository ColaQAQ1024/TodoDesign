package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.dto.TeammateDTO;
import com.todoDesign.entity.Teammate;
import com.todoDesign.entity.User;
import com.todoDesign.mapper.GroupMapper;
import com.todoDesign.mapper.TeammateMapper;
import com.todoDesign.mapper.UserMapper;
import com.todoDesign.service.ITeammateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mory
 * &date  2023/8/17 11:36
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Service
@RequiredArgsConstructor
public class TeammateService extends ServiceImpl<TeammateMapper, Teammate> implements ITeammateService {

    private final GroupMapper groupMapper;
    private final UserMapper userMapper;

    //为列表添加新用户
    @Override
    public ResponseEntity<String> inviteTeammate(TeammateDTO teammateDTO) {

        //检索列表是否存在，不存在为null
        Integer groupId = groupMapper.getGroupIdByGroupNameAndUserId(teammateDTO.getGroupName(), StpUtil.getLoginIdAsInt());
        if (groupId == null) {
            return ResponseEntity.ok("列表不存在");
        }

        //检索目标用户是否存在，不存在为null
        User user = userMapper.getUserByUsername(teammateDTO.getTargetUsername());
        if (user == null) {
            return ResponseEntity.ok(teammateDTO.getTargetUsername() + "不存在");
        }

        //检查用户是否已经在列表的协助人员中，不存在为null
        Teammate teammate = this.lambdaQuery()
                .eq(Teammate::getGroupId,groupId)
                .eq(Teammate::getUserId,user.getUserId())
                .one();
        if (teammate != null){
            return ResponseEntity.ok(teammateDTO.getTargetUsername() + "已存在列表中");
        }

        teammate = new Teammate(groupId,user.getUserId());
        teammate.setUserId(user.getUserId());
        this.save(teammate);
        return ResponseEntity.ok(teammateDTO.getTargetUsername() + "已加入");
    }

    @Override
    public void saveByGroupIdAndUserId(int groupId, int userId) {
        Teammate teammate = new Teammate(groupId,userId);
        this.save(teammate);
    }
}
