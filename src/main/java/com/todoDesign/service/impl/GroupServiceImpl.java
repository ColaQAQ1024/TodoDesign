package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.entity.Group;
import com.todoDesign.configure.RedisUtil;
import com.todoDesign.entity.User;
import com.todoDesign.mapper.GroupMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.ITeammateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mory
 */
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    private final ITeammateService iTeammateService;
    private final GroupMapper groupMapper;
    private final RedisUtil redisUtil;

    private static final String COUNTER_KEY = "counter";
    private static final String USER_PREFIX = "user:";//键名


    //通过列表名和userId获得对应的groupId
    @Override
    public int getGroupIdByGroupNameAndUserId(String groupName, int userId) {
        Integer groupId = groupMapper.getGroupIdByGroupNameAndUserId(groupName,userId);
        return (groupId != null) ? groupId : -1;
    }

    //创建新列表
    @Override
    public ResponseEntity<String> createGroup(Group group) {
        int userId = StpUtil.getLoginIdAsInt();
        if (groupMapper.getGroupIdByGroupNameAndUserId(group.getGroupName(),userId) == null) {
            this.save(group);
            iTeammateService.saveByGroupIdAndUserId(group.getGroupId(),userId);
            return ResponseEntity.ok("列表：" + group.getGroupName() + " 创建成功");
        }else {
            return ResponseEntity.ok("列表：" + group.getGroupName() + " 已存在");
        }
    }

    @Override
    public Long incrementCounter() {
        return redisUtil.increment(COUNTER_KEY,1);//键值递增
    }

    @Override
    public Integer getCounter() {
        Integer counterValue = (Integer) redisUtil.get(COUNTER_KEY);
        return counterValue != null ? counterValue : -1;
    }

    @Override
    public void cacheUser(User user){
        redisUtil.rightPush(USER_PREFIX, user);
    }

    @Override
    public List<Object> getAllUserFromCache() {
        return redisUtil.range(USER_PREFIX,0,-1);
    }

    public void persistUser(User user) {
        //内存 -> 储存 转换数据库
    }
}