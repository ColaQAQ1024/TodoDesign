package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@TableName("todo_quest")
@Data
public class Quest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nameThing;
    @TableId(value = "quest_id", type = IdType.AUTO)
    private Integer questId;
}
