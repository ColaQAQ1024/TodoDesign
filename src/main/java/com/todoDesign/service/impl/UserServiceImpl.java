package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.dto.UserDTO;
import com.todoDesign.entity.Group;
import com.todoDesign.entity.Relation;
import com.todoDesign.entity.User;
import com.todoDesign.mapper.UserMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IRelationService;
import com.todoDesign.service.ITeammateService;
import com.todoDesign.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final ITeammateService iTeammateService;
    private final IGroupService iGroupService;
    private final IRelationService iRelationService;

    @Override
    public ResponseEntity<String> signIn(@NotNull User user){
        if ("String".equals(user.getNickname())){
            //为新用户设置随机昵称 - 格式为：新用户_（4）随机数
            user.setNickname("新用户_" + String.format("%04d",new Random().nextInt(10000)));
        }
        if (this.lambdaQuery()
                .eq(User::getUsername,user.getUsername())
                .one()
                == null
        ){
            this.save(user);
            Integer userId = this.lambdaQuery()
                    .eq(User::getUsername,user.getUsername())
                    .one()
                    .getUserId();
            Group group = new Group("任务");
            iGroupService.save(group);
            // 使用保存后的用户信息调用 createGroup 方法
            iTeammateService.saveByGroupIdAndUserId(group.getGroupId(),userId);
            Relation relation = new Relation(2,userId);//默认标准用户
            // 建立User权限关系
            iRelationService.save(relation);
            return ResponseEntity.ok("注册成功");
        }else {
            return ResponseEntity.ok("用户名重复，换一个试试看吧！");
        }
    }

    @Override
    public ResponseEntity<String> login(UserDTO userDTO){
        User user = this.lambdaQuery()
                .eq(User::getUsername, userDTO.getUsername())
                .eq(User::getPassword, userDTO.getPassword())
                .one();
        if (user != null) {
            if (StpUtil.isDisable(user.getUserId())){
                //检查userId是否已被封禁
                return ResponseEntity.ok("当前账号异常,解除时间:" + StpUtil.getDisableTime(user.getUserId()));
            }
            StpUtil.login(user.getUserId());//成功登录
            return ResponseEntity.ok("登录成功");
        }else {
            return ResponseEntity.ok("用户名或密码错误(⊙_⊙)，请重新输入");
        }
    }

}
