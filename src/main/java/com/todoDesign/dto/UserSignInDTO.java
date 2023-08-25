package com.todoDesign.dto;

import lombok.Data;

/**
 * @author Mory
 * &date  2023/8/25 10:10
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
public class UserSignInDTO {
    private String username;
    private String password;
    private String nickname;
}
