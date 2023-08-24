package com.todoDesign.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.todoDesign.dto.GroupDTO;
import com.todoDesign.dto.Money;
import com.todoDesign.dto.TeammateDTO;
import com.todoDesign.entity.Group;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.IRelationService;
import com.todoDesign.service.ITeammateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Controller
@SaCheckLogin
@RequiredArgsConstructor
@RequestMapping("/todoDesign/group")
public class GroupController {

    private final IGroupService iGroupService;
    private final ITeammateService iTeammateService;
    private final IRelationService iRelationService;

    @SaCheckRole("user-plus")
    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@RequestBody GroupDTO groupDTO){
        Group group = new Group(groupDTO.getGroupName());
        return iGroupService.createGroup(group);
    }

    @SaCheckRole("user-plus")
    @PostMapping("/Teammate")
    public ResponseEntity<String> inviteTeammate(@RequestBody TeammateDTO teammateDTO){
        return iTeammateService.inviteTeammate(teammateDTO);
    }

    @SaCheckLogin
    @PostMapping("/subscribeTodo")
    public ResponseEntity<String> subscribeTodo(@RequestBody Money money){
        return iRelationService.subscribeTodo(money);
    }

    @GetMapping("/increment")
    public Long incrementCount() {
        return iGroupService.incrementCounter();
    }

    @GetMapping("/getCounter")
    public Integer getCounter() {
        return iGroupService.getCounter();
    }
}
