package com.todoDesign.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.todoDesign.dto.GroupDTO;
import com.todoDesign.dto.TeammateDTO;
import com.todoDesign.entity.Group;
import com.todoDesign.service.IGroupService;
import com.todoDesign.service.ITeammateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@SaCheckLogin
@RequiredArgsConstructor
@RequestMapping("/todoDesign/group")
public class GroupController {

    private final IGroupService iGroupService;
    private final ITeammateService iTeammateService;

    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@RequestBody GroupDTO groupDTO){
        Group group = new Group(groupDTO.getGroupName());
        return iGroupService.createGroup(group);
    }

    @PostMapping("/Teammate")
    public ResponseEntity<String> inviteTeammate(@RequestBody TeammateDTO teammateDTO){
        return iTeammateService.inviteTeammate(teammateDTO);
    }
}
