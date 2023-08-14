package com.todoDesign.service.impl;

import com.todoDesign.dto.QuestDTO;
import com.todoDesign.dto.FinishQuest;
import com.todoDesign.dto.ZModelMapperConfig;
import com.todoDesign.entity.GroupQuest;
import com.todoDesign.entity.Quest;
import com.todoDesign.mapper.GroupQuestMapper;
import com.todoDesign.mapper.QuestMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IQuestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Service
public class QuestServiceImpl extends ServiceImpl<QuestMapper, Quest> implements IQuestService {

    private final QuestMapper questMapper;
    private final GroupQuestMapper groupQuestMapper;
    private final ModelMapper modelMapper;
    private final IGroupService iGroupService;
    private final ZModelMapperConfig zModelMapperConfig;

    public QuestServiceImpl(QuestMapper questMapper, GroupQuestMapper groupQuestMapper, ModelMapper modelMapper, IGroupService iGroupService, ZModelMapperConfig zModelMapperConfig) {
        this.questMapper = questMapper;
        this.groupQuestMapper = groupQuestMapper;
        this.modelMapper = modelMapper;
        this.iGroupService = iGroupService;
        this.zModelMapperConfig = zModelMapperConfig;
    }

    //更新group_quest表的GroupId和questId
    private void connectQuestAndGroup(int questId,int groupId){
        GroupQuest groupQuest = new GroupQuest();
        groupQuest.setQuestId(questId);
        groupQuest.setGroupId(groupId);
        groupQuestMapper.insert(groupQuest);
    }

    //通过事项和groupId获得对应的questId
    @Override
    public int getQuestIdByNameThingAndGroupId(String nameThing,int groupId){
        Quest quest = this.getOne(this.query().select("quest_id")
                .inSql("quest_id","SELECT quest_id FROM todo_group_quest WHERE group_id = " + groupId)
                .eq("name_thing",nameThing));
        return (quest != null) ? quest.getQuestId() : -1;
    }

    @Override
    public ResponseEntity<String> add(QuestDTO questDTO, int userId){
        Quest quest = modelMapper.map(questDTO,Quest.class);
        questMapper.insert(quest);
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(),userId);
        this.connectQuestAndGroup(quest.getQuestId(),groupId);
        return ResponseEntity.ok(quest.getNameThing() + "已添加列表" + questDTO.getNameThing());
    }

    @Override
    public ResponseEntity<Object> allFinish(String groupName,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        List<FinishQuest> finishQuests = zModelMapperConfig.convertList(this.list(this.query().select("name_thing","start_time")
                .inSql("quest_id","SELECT quest_id FROM todo_group_quest WHERE group_id = " + groupId)
                .eq("finish",true)),FinishQuest.class);//设置查询条件
        if (finishQuests.size() > 0){
            return ResponseEntity.ok(finishQuests);
        }else {
            return ResponseEntity.ok("暂无已完成事项");
        }
    }

    @Override
    public ResponseEntity<Object> planning(int userId){
        List<FinishQuest> plan = questMapper.planning(userId);
        if (!plan.isEmpty()){
            return ResponseEntity.ok(plan);
        }else {
            return ResponseEntity.ok("没有计划中的任务喔！");
        }
    }

    @Override
    public ResponseEntity<String> finish(QuestDTO questDTO,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(),userId);
        int questId = this.getQuestIdByNameThingAndGroupId(questDTO.getNameThing(),groupId);
        Quest finishQuest = new Quest();
        finishQuest.setFinish(true);
        if (this.update(finishQuest,this.query().eq("quest_id",questId))){
            return ResponseEntity.ok(questDTO.getNameThing() + "已完成");
        }else {
            return ResponseEntity.ok("未找到对应事项");
        }
    }

    @Override
    public ResponseEntity<String> deleteThing(QuestDTO questDTO,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(),userId);
        int questId = this.getQuestIdByNameThingAndGroupId(questDTO.getNameThing(),groupId);
        if (this.removeById(questId)){
            return ResponseEntity.ok(questDTO.getNameThing() + "成功删除");
        }else {
            return ResponseEntity.ok("未找到对应事项");
        }
    }

    @Override
    public ResponseEntity<String> setStar(QuestDTO questDTO,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(),userId);
        int questId = this.getQuestIdByNameThingAndGroupId(questDTO.getNameThing(),groupId);
        Quest importantQuest = new Quest();
        importantQuest.setStar(true);
        if (this.update(importantQuest,this.query().eq("quest_id",questId))){
            return ResponseEntity.ok(questDTO.getNameThing() + "已标记为重要事项");
        }else {
            return ResponseEntity.ok("未找到对应事项");
        }
    }


}
