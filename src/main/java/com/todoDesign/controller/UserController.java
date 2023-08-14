package com.todoDesign.controller;

import com.todoDesign.entity.User;
import com.todoDesign.dto.UserDTO;
import com.todoDesign.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Controller
@RequestMapping("/todoDesign/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!在这里树立你的目标吧！(*^▽^*)");
    }

    @PostMapping("/users")
    public ResponseEntity<String> signIn(@RequestBody User user){
        return userService.signIn(user);
    }

    @GetMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO, HttpSession session){
        return userService.login(userDTO,session);
    }

}
