package com.todoDesign.controller;

import com.todoDesign.entity.FinishQuest;
import com.todoDesign.entity.Quest;
import com.todoDesign.mapper.QuestMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IQuestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Controller
@RequestMapping("/todoDesign/quest")
public class QuestController {

    private final IQuestService iQuestService;
    private final IGroupService iGroupService;
    private final QuestMapper questMapper;

    public QuestController(IQuestService iQuestService, IGroupService iGroupService, QuestMapper questMapper) {
        this.iQuestService = iQuestService;
        this.iGroupService = iGroupService;
        this.questMapper = questMapper;
    }

    @PostMapping("/todos/{groupName}")
    public ResponseEntity<String> add(@PathVariable String groupName, @RequestBody Quest quest, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        if(groupId > 0){
            return iQuestService.add(quest,groupId);
        }else {
            return ResponseEntity.ok("列表不存在");
        }
    }

    @GetMapping("/unFinish/{groupName}")
    public ResponseEntity<List<FinishQuest>> unFinish(@PathVariable String groupName, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
            return ResponseEntity.ok(questMapper.unFinishQuest(groupId));
    }

    @GetMapping("/allFinish/{groupName}")
    public ResponseEntity<Object> allFinish(@PathVariable String groupName, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        List<FinishQuest> finishQuests = questMapper.allFinish(groupId);
        if (finishQuests.size() > 0){
            return ResponseEntity.ok(finishQuests);
        }else {
            return ResponseEntity.ok("暂无已完成事项");
        }
    }

    @GetMapping("/planing")
    public  ResponseEntity<Object> planing(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        List<FinishQuest>  plan = questMapper.planning(userId);
        if (!plan.isEmpty()){
            return ResponseEntity.ok(plan);
        }else {
            return ResponseEntity.ok("没有计划中的任务喔！");
        }
    }

    @PutMapping("/finishThing/{thing}/{groupName}")
    public ResponseEntity<String> finishThing(
            @PathVariable String groupName,
            @PathVariable String thing,
            HttpSession session
    ){
        Integer userId = (Integer) session.getAttribute("userId");
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        if(questMapper.finishThing(thing,groupId) > 0){
            return ResponseEntity.ok(thing + "已完成");
        }else {
            return ResponseEntity.ok("事项不存在");
        }
    }
}
