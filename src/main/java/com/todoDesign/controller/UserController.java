package com.todoDesign.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.todoDesign.dto.UserDTO;
import com.todoDesign.entity.User;
import com.todoDesign.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@RestController
@RequestMapping("/todoDesign/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @SaIgnore
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!在这里树立你的目标吧！(*^▽^*)");
    }

    @SaIgnore
    @PostMapping("/users")
    public ResponseEntity<String> signIn(@RequestBody User user){
        return userService.signIn(user);
    }

    @SaIgnore
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    // 查询登录状态
    @SaIgnore
    @RequestMapping("/isLogin")
    public ResponseEntity<Object> isLogin() {
        return ResponseEntity.ok("当前会话是否登录：" + StpUtil.isLogin() + " userId:" + StpUtil.getLoginIdDefaultNull());
    }

    @SaCheckLogin
    @PostMapping("/logOut")
    public ResponseEntity<Object> logOut(){
        StpUtil.logout();
        return ResponseEntity.ok("");
    }

}
