package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mory
 * &date  2023/8/16 11:15
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> permission = new ArrayList<>();
        permission.add("101");
        permission.add("user.add");
        permission.add("user.update");
        permission.add("user.get");
        // permission.add("user.delete");
        permission.add("art.*");
        return permission;

    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> role = new ArrayList<>();
        role.add("admin");
        role.add("super-admin");
        return role;

    }
}
