package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.todoDesign.entity.Relation;
import com.todoDesign.entity.Roles;
import com.todoDesign.service.IRelationService;
import com.todoDesign.service.IRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mory
 * &date  2023/8/16 11:15
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final IRolesService iRolesService;
    private final IRelationService iRelationService;

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

        List<String> role = new ArrayList<>();
        //获取角色标识id
        int roleId = iRelationService.lambdaQuery().eq(Relation::getUserId,loginId).one().getRoleId();

        switch (roleId){
            case 1 ->{
                //加载master权限
                String roles =  iRolesService.lambdaQuery().eq(Roles::getRoleName,"master").one().getRoleName();
                role.add(roles);
            }
            case 2 ->{
                //加载标准用户权限
                String roles =  iRolesService.lambdaQuery().eq(Roles::getRoleName,"user").one().getRoleName();
                role.add(roles);
            }
            case 3 ->{
                //加载订阅用户权限
                List<String> roles = new ArrayList<>();
                roles.add(iRolesService.lambdaQuery().eq(Roles::getRoleName,"user").one().getRoleName());
                roles.add(iRolesService.lambdaQuery().eq(Roles::getRoleName,"user-plus").one().getRoleName());
                //注意此处把roles的数组交给role
                role = roles;
            }
        }

        return role;
    }
}
