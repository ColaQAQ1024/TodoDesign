package com.todoDesign.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.entity.Roles;
import com.todoDesign.mapper.RoleMapper;
import com.todoDesign.service.IRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mory
 * &date  2023/8/18 14:46
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */

@Service
@RequiredArgsConstructor
public class RolesServiceImpl extends ServiceImpl<RoleMapper, Roles> implements IRolesService {

}
