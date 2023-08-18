package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;

/**
 * @author Mory
 * &date  2023/8/18 14:13
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@TableName("todo_role")
@Data
public class Roles {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "column_id", type = IdType.AUTO)
    private int columnId;

    private String roleName;
    private String introduce;
}
