package com.todoDesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@TableName("todo_quest")
public class Quest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer groupId;

    private String nameThing;

    private LocalDateTime startTime;

    private Boolean finish;

    private Boolean star;

    private LocalDateTime deadTime;

    @TableId(value = "quest_id", type = IdType.AUTO)
    private Integer questId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getNameThing() {
        return nameThing;
    }

    public void setNameThing(String nameThing) {
        this.nameThing = nameThing;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public LocalDateTime getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(LocalDateTime deadTime) {
        this.deadTime = deadTime;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    @Override
    public String toString() {
        return "Quest{" +
        "groupId = " + groupId +
        ", nameThing = " + nameThing +
        ", startTime = " + startTime +
        ", finish = " + finish +
        ", star = " + star +
        ", deadTime = " + deadTime +
        ", questId = " + questId +
        "}";
    }
}
