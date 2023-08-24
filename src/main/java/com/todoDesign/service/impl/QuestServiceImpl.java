package com.todoDesign.service.impl;

import com.todoDesign.dto.QuestDTO;
import com.todoDesign.dto.FinishQuest;
import com.todoDesign.congigure.ZModelMapperConfig;
import com.todoDesign.entity.Quest;
import com.todoDesign.mapper.QuestMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IQuestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Service
@RequiredArgsConstructor
public class QuestServiceImpl extends ServiceImpl<QuestMapper, Quest> implements IQuestService {

    private final QuestMapper questMapper;
    private final ModelMapper modelMapper;
    private final IGroupService iGroupService;
    private final ZModelMapperConfig zModelMapperConfig;


    //通过事项和groupId获得对应的questId
    @Override
    public int getQuestIdByNameThingAndGroupId(String nameThing,int groupId){
        Quest quest = this.lambdaQuery()
                .eq(Quest::getNameThing,nameThing)
                .eq(Quest::getGroupId,groupId)
                .one();
        return (quest != null) ? quest.getQuestId() : -1;
    }

    @Override
    public ResponseEntity<String> add(QuestDTO questDTO, int userId){
        Quest quest = modelMapper.map(questDTO,Quest.class);
        Integer groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(),userId);
        quest.setGroupId(groupId);
        this.save(quest);
        return ResponseEntity.ok(quest.getNameThing() + "已添加列表" + questDTO.getGroupName());
    }

    @Override
    public ResponseEntity<Object> allFinish(String groupName,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        List<Quest> list = this.lambdaQuery()
                .eq(Quest::isFinish,true)
                .eq(Quest::getGroupId,groupId)
                .list();//设置查询条件
        List<FinishQuest> finishQuests = zModelMapperConfig.convertList(list,FinishQuest.class);//类型转换
        if (finishQuests.size() > 0){
            return ResponseEntity.ok(finishQuests);
        }else {
            return ResponseEntity.ok("暂无已完成事项");
        }
    }

    @Override
    public ResponseEntity<Object> unFinish(String groupName,int userId){
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        List<Quest> list = this.lambdaQuery()
                .eq(Quest::isFinish,false)
                .eq(Quest::getGroupId,groupId)
                .list();//设置查询条件
        List<FinishQuest> finishQuests = zModelMapperConfig.convertList(list,FinishQuest.class);//类型转换
        if (finishQuests.size() > 0){
            return ResponseEntity.ok(finishQuests);
        }else {
            return ResponseEntity.ok("暂无为完成事项");
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
        Quest finishQuest = Optional.ofNullable(this.getById(questId)).orElseThrow();
        finishQuest.setFinish(true);
        if (this.updateById(finishQuest)){
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
        Quest quest = Optional.ofNullable(this.getById(questId)).orElseThrow();
        quest.setStar(true);
        if (this.updateById(quest)){
            return ResponseEntity.ok(questDTO.getNameThing() + "已标记为重要事项");
        }else {
            return ResponseEntity.ok("未找到对应事项");
        }
    }

}
