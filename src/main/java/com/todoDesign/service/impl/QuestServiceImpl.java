package com.todoDesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.todoDesign.dto.QuestDTO;
import com.todoDesign.dto.FinishQuest;
import com.todoDesign.entity.Group;
import com.todoDesign.entity.GroupQuest;
import com.todoDesign.entity.Quest;
import com.todoDesign.mapper.GroupMapper;
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
    private final GroupMapper groupMapper;
    private final ModelMapper modelMapper;
    private final IGroupService iGroupService;

    public QuestServiceImpl(QuestMapper questMapper, GroupQuestMapper groupQuestMapper, GroupMapper groupMapper, ModelMapper modelMapper, IGroupService iGroupService) {
        this.questMapper = questMapper;
        this.groupQuestMapper = groupQuestMapper;
        this.groupMapper = groupMapper;
        this.modelMapper = modelMapper;
        this.iGroupService = iGroupService;
    }

    //连接对应GroupId和questId
    private void connectQuestAndGroup(int questId,int groupId){
        GroupQuest groupQuest = new GroupQuest();
        groupQuest.setQuestId(questId);
        groupQuest.setGroupId(groupId);
        groupQuestMapper.insert(groupQuest);
    }

    @Override
    public ResponseEntity<String> add(QuestDTO questDTO, int userId){
        Quest quest = modelMapper.map(questDTO,Quest.class);
        questMapper.insert(quest);
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(),userId);
        this.connectQuestAndGroup(quest.getQuestId(),groupId);
        return ResponseEntity.ok(quest.getNameThing() + "已添加列表" + groupMapper.selectOne(new QueryWrapper<Group>().eq("group_id",groupId)).getGroupName());
    }

    @Override
    public ResponseEntity<Object> allFinish(String groupName,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        List<FinishQuest> finishQuests = questMapper.allFinish(groupId);
        if (finishQuests.size() > 0){
            return ResponseEntity.ok(finishQuests);
        }else {
            return ResponseEntity.ok("暂无已完成事项");
        }
    }

    @Override
    public ResponseEntity<Object> planing(int userId){
        List<FinishQuest> plan = questMapper.planning(userId);
        if (!plan.isEmpty()){
            return ResponseEntity.ok(plan);
        }else {
            return ResponseEntity.ok("没有计划中的任务喔！");
        }
    }

    @Override
    public ResponseEntity<String> finish(QuestDTO questDTO,int groupId){
        if(questMapper.finishThing(questDTO.getNameThing(), groupId) > 0){
            return ResponseEntity.ok(questDTO.getNameThing() + "已完成");
        }else {
            return ResponseEntity.ok("事项不存在");
        }
    }

}
