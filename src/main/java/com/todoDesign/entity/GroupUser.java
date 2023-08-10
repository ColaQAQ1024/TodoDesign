package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Mory
 * &date  2023/8/10 17:00
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@TableName("todo_user_group")
@Data
public class GroupUser {
    private Integer userId;
    private Integer groupId;
}