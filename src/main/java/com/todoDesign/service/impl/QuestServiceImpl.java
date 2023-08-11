package com.todoDesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.todoDesign.entity.Group;
import com.todoDesign.entity.GroupQuest;
import com.todoDesign.entity.Quest;
import com.todoDesign.mapper.GroupMapper;
import com.todoDesign.mapper.GroupQuestMapper;
import com.todoDesign.mapper.QuestMapper;
import com.todoDesign.service.IQuestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public QuestServiceImpl(QuestMapper questMapper, GroupQuestMapper groupQuestMapper, GroupMapper groupMapper) {
        this.questMapper = questMapper;
        this.groupQuestMapper = groupQuestMapper;
        this.groupMapper = groupMapper;
    }
    
    //连接对应GroupId和questId
    private void connectQuestAndGroup(int questId,int groupId){
        GroupQuest groupQuest = new GroupQuest();
        groupQuest.setQuestId(questId);
        groupQuest.setGroupId(groupId);
        groupQuestMapper.insert(groupQuest);
    }

    @Override
    public ResponseEntity<String> add(Quest quest, int groupId){
        questMapper.insert(quest);
        this.connectQuestAndGroup(quest.getQuestId(), groupId);
        return ResponseEntity.ok(quest.getNameThing() + "已添加列表" + groupMapper.selectOne(new QueryWrapper<Group>().eq("group_id",groupId)).getGroupName());
    }

}
