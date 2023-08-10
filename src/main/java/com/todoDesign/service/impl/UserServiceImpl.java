package com.todoDesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.todoDesign.entity.Group;
import com.todoDesign.entity.User;
import com.todoDesign.mapper.UserMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;
    private final IGroupService iGroupService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, IGroupService iGroupService) {
        this.userMapper = userMapper;
        this.iGroupService = iGroupService;
    }

    public int getUserIdByUserName(String userName){
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",userName)).getUserId();
    }

    @Override
    public ResponseEntity<String> signIn(@NotNull User user){
        if ("String".equals(user.getNickname())){
            //为新用户设置随机昵称 - 格式为：新用户_（4）随机数
            user.setNickname("新用户_" + String.format("%04d",new Random().nextInt(10000)));
        }
        //检验用户名是否被占用
        if (userMapper.selectOne(new QueryWrapper<User>().eq("username",user.getUsername())) == null){
            userMapper.insert(user);
            iGroupService.createGroup(new Group("任务"),user.getUserId());
            return ResponseEntity.ok("注册成功");
        }else {
            return ResponseEntity.ok("用户名重复，换一个试试看吧！");
        }
    }

    @Override
    public ResponseEntity<String> login(String userName, String password, HttpSession session){
        if (userMapper.selectOne(new QueryWrapper<User>().eq("username",userName).eq("password",password)) != null){
            session.setAttribute("userId",this.getUserIdByUserName(userName));
            return ResponseEntity.ok("登录成功");
        }else {
            return ResponseEntity.ok("用户名或密码错误(⊙_⊙)，请重新输入");
        }
    }


}
