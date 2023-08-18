package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.dto.TeammateDTO;
import com.todoDesign.entity.Teammate;
import org.springframework.http.ResponseEntity;

/**
 * 团队成员 Service 接口
 * <p>
 * 这是一个用于处理团队成员的 Service 接口。
 * 通过继承 IService<Teammate>，可以使用 MyBatis-Plus 提供的通用 CRUD 方法。
 *
 * @author Mory
 * @since 2023-08-17
 */
public interface ITeammateService extends IService<Teammate> {

    /**
     * 邀请团队成员
     *
     * @param teammateDTO 团队成员DTO
     * @return 响应实体，包含邀请结果信息
     */
    ResponseEntity<String> inviteTeammate(TeammateDTO teammateDTO);

    /**
     * 根据团队ID和用户ID保存团队成员信息
     *
     * @param groupId 团队ID
     * @param userId  用户ID
     */
    void saveByGroupIdAndUserId(int groupId, int userId);
}
