package com.todoDesign.controller;

import com.todoDesign.dto.QuestDTO;
import com.todoDesign.dto.FinishQuest;
import com.todoDesign.mapper.QuestMapper;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IQuestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Mory
 */
@Controller
@RequestMapping("/todoDesign/quest")
public class QuestController {

    private final IQuestService questService;
    private final IGroupService groupService;
    private final QuestMapper questMapper;

    public QuestController(IQuestService questService, IGroupService groupService, QuestMapper questMapper) {
        this.questService = questService;
        this.groupService = groupService;
        this.questMapper = questMapper;
    }

    @PostMapping("/todos")
    public ResponseEntity<String> addQuest(@RequestBody QuestDTO questDTO, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return questService.add(questDTO, userId);
    }

    @GetMapping("/unFinish/{groupName}")
    public ResponseEntity<List<FinishQuest>> getUnfinishedQuests(@PathVariable String groupName, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        int groupId = groupService.getGroupIdByGroupNameAndUserId(groupName, userId);
        return ResponseEntity.ok(questMapper.unFinishQuest(groupId));
    }

    @GetMapping("/allFinish/{groupName}")
    public ResponseEntity<Object> getAllFinishedQuests(@PathVariable String groupName, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return questService.allFinish(groupName, userId);
    }

    @GetMapping("/planing")
    public ResponseEntity<Object> getPlannedQuests(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return questService.planning(userId);
    }

    @PutMapping("/finishQuest")
    public ResponseEntity<String> finishQuest(@RequestBody QuestDTO questDTO, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return questService.finish(questDTO, userId);
    }

    @DeleteMapping("/deleteQuest")
    public ResponseEntity<String> deleteThing(@RequestBody QuestDTO questDTO, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        return questService.deleteThing(questDTO,userId);
    }

    @PostMapping ResponseEntity<String> setStar(@RequestBody QuestDTO questDTO,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        return questService.setStar(questDTO,userId);
    }
}
