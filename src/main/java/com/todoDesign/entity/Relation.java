package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author Mory
 * &date  2023/8/18 15:08
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@TableName("todo_relation")
@Data
public class Relation {

    private static final long serialVersionUID = 1L;

    @TableId(value = "column_id", type = IdType.AUTO)
    private Integer columnId;

    private int userId;
    private int roleId;

    public Relation(int roleId, int userid) {
        this.roleId = roleId;
        this.userId = userid;
    }
}
