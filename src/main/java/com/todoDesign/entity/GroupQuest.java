package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Mory
 * &date  2023/8/10 17:00
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@TableName("todo_group_quest")
@Data
public class GroupQuest {
    private Integer questId;
    private Integer groupId;
    @TableId(value = "column", type = IdType.AUTO)
    private Integer columnId;
}