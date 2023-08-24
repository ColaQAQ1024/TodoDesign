package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mory
 * &date  2023/8/24 9:47
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
@TableName("table_check")
public class Check implements Serializable {
    //订单
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(value = "check_id",type = IdType.AUTO)
    private int checkId;
    private int userId;
    private boolean checkStatus;
    private LocalDateTime createTime;
    private int amount;
    private String moneyType;

}
