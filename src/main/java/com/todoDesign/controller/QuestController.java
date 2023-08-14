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

    @PostMapping("/todos")
    public ResponseEntity<String> add(@RequestBody QuestDTO questDTO, HttpSession session){Integer userId = (Integer) session.getAttribute("userId");
        return iQuestService.add(questDTO,userId);
    }

    @GetMapping("/unFinish/{groupName}")
    public ResponseEntity<List<FinishQuest>> unFinish(@PathVariable String groupName, HttpSession session){Integer userId = (Integer) session.getAttribute("userId");
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(groupName,userId);
        return ResponseEntity.ok(questMapper.unFinishQuest(groupId));
    }
    @GetMapping("/allFinish/{groupName}")
    public ResponseEntity<Object> allFinish(@PathVariable String groupName, HttpSession session){Integer userId = (Integer) session.getAttribute("userId");
        return iQuestService.allFinish(groupName,userId);
    }

    @GetMapping("/planing")
    public  ResponseEntity<Object> planing(HttpSession session){Integer userId = (Integer) session.getAttribute("userId");
        return iQuestService.planing(userId);
    }

    @PutMapping("/finishThing")
    public ResponseEntity<String> finishThing(@RequestBody QuestDTO questDTO, HttpSession session){Integer userId = (Integer) session.getAttribute("userId");
        int groupId = iGroupService.getGroupIdByGroupNameAndUserId(questDTO.getGroupName(), userId);
        return iQuestService.finish(questDTO,groupId);
    }
}