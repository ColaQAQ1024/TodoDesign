package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Mory
 * &date  2023/8/17 11:13
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
@TableName("todo_teammate")
public class Teammate implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer groupId;
    private Integer userId;
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer columnId;

    public Teammate(Integer groupId, Integer userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
