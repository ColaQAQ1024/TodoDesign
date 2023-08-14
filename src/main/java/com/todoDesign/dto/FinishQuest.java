package com.todoDesign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadTime;
    private String nameThing;
}