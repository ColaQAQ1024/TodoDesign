package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@EqualsAndHashCode(callSuper = true)
@TableName("todo_quest")
@Data
@Accessors(chain = true)
public class Quest extends Model<Quest> {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nameThing;
    @TableId(value = "quest_id", type = IdType.AUTO)
    private Integer questId;
    private boolean finish;
    private boolean star;
    private Integer groupId;
}
