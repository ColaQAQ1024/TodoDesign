package com.todoDesign.controller;

import com.todoDesign.entity.Quest;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IQuestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

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

    public QuestController(IQuestService iQuestService, IGroupService iGroupService) {
        this.iQuestService = iQuestService;
        this.iGroupService = iGroupService;
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

}
