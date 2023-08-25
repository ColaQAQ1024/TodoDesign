package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.configure.Big;
import com.todoDesign.dto.UserDTO;
import com.todoDesign.dto.UserSignInDTO;
import com.todoDesign.entity.User;
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
    ResponseEntity<String> signIn(@NotNull UserSignInDTO user);

    /**
     * 用户登录
     *
     * @param userDTO 用户登录信息DTO
     * @return 响应实体
     */
    ResponseEntity<String> login(UserDTO userDTO);

    Big<String> setLuckyKey(String luckyPassword);

    Big<String> loginLuckyLock(String luckyPassword);
}
