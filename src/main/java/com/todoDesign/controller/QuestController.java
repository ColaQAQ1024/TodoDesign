package com.todoDesign.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.todoDesign.dto.QuestDTO;
import com.todoDesign.service.IQuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


/**
 * @author Mory
 */
@Controller
@RequestMapping("/todoDesign/quest")
@RequiredArgsConstructor
@SaCheckLogin
public class QuestController {

    private final IQuestService iquestService;

    @PostMapping("/todos")
    public ResponseEntity<String> addQuest(@RequestBody QuestDTO questDTO) {
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.add(questDTO, userId);
    }

    @GetMapping("/unFinish/{groupName}")
    public ResponseEntity<Object> getUnfinishedQuests(@PathVariable String groupName) {
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.unFinish(groupName, userId);
    }

    @GetMapping("/allFinish/{groupName}")
    public ResponseEntity<Object> getAllFinishedQuests(@PathVariable String groupName) {
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.allFinish(groupName, userId);
    }

    @GetMapping("/planing")
    public ResponseEntity<Object> getPlannedQuests() {
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.planning(userId);
    }

    @PutMapping("/finishQuest")
    public ResponseEntity<String> finishQuest(@RequestBody QuestDTO questDTO) {
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.finish(questDTO, userId);
    }

    @DeleteMapping("/deleteQuest")
    public ResponseEntity<String> deleteThing(@RequestBody QuestDTO questDTO){
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.deleteThing(questDTO,userId);
    }

    @PostMapping ("/setStar")
    ResponseEntity<String> setStar(@RequestBody QuestDTO questDTO){
        Integer userId = (Integer) StpUtil.getSession().get("userId");
        return iquestService.setStar(questDTO,userId);
    }
}
