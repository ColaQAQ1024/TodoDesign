package com.todoDesign.service;

import com.todoDesign.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
public interface IUserService extends IService<User> {


    /*
    注册新用户
    */
    ResponseEntity<String> signIn(@NotNull User user);

    /*
    用户登录
    */
    ResponseEntity<String> login(String userName, String password, HttpSession session);

}
