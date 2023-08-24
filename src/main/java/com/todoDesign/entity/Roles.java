package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author Mory
 * &date  2023/8/18 14:13
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
@TableName("todo_roles")
public class Roles {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    private String roleName;
    private String introduce;
}
