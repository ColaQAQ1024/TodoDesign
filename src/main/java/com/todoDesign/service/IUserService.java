package com.todoDesign.service;

import com.todoDesign.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

/**
 * 用户服务接口
 * @author Mory
 */
public interface IUserService extends IService<User> {

    /**
     * 注册新用户
     *
     * @param user 要注册的用户信息
     * @return 响应实体
     */
    ResponseEntity<String> signIn(@NotNull User user);

    /**
     * 用户登录
     *
     * @param userDTO 用户登录信息DTO
     * @param session HTTP会话
     * @return 响应实体
     */
    ResponseEntity<String> login(UserDTO userDTO, HttpSession session);
}
