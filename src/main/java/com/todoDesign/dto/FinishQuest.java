package com.todoDesign.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mory
 * &date  2023/7/26 16:14
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
public class FinishQuest implements Serializable {
    private LocalDateTime startTime;
    private LocalDateTime deadTime;
    private String nameThing;
}