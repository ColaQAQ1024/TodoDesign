package com.todoDesign.controller;

import com.todoDesign.entity.Group;
import com.todoDesign.service.IGroupService;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/todoDesign/group")
public class GroupController {

    private final IGroupService iGroupService;

    public GroupController(IGroupService iGroupService) {
        this.iGroupService = iGroupService;
    }

    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@RequestBody Group group, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        return iGroupService.createGroup(group,userId);
    }


}
