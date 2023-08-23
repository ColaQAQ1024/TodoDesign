package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.dto.TeammateDTO;
import com.todoDesign.entity.Teammate;
import org.springframework.http.ResponseEntity;

/**
 * @author Mory
 * &date  2023/8/17 11:17
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
public interface ITeammateService extends IService<Teammate> {

    ResponseEntity<String> inviteTeammate(TeammateDTO teammateDTO);

    void saveByGroupIdAndUserId(int groupId,int userId);
}
