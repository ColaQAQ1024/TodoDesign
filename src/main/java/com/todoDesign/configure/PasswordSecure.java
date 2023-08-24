package com.todoDesign.configure;

import cn.dev33.satoken.secure.SaBase64Util;
import lombok.Data;

/**
 * @author Mory
 * &date  2023/8/23 14:38
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
public class PasswordSecure {

    //加密密码
    public String setPassword(String password){
        return SaBase64Util.encode(password);
    }

    //获取密码
    public String getPassword(String pw_hash){
        return SaBase64Util.decode(pw_hash);
    }
}
