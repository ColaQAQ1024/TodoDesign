package com.todoDesign.mapper;

import com.todoDesign.entity.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 小组实体的数据库映射接口
 * @author Mory
 */
@Repository
public interface GroupMapper extends BaseMapper<Group> {

    /**
     * 根据小组名称和用户ID获取小组ID
     *
     * @param groupName 小组名称
     * @param userId 用户ID
     * @return 小组ID
     */
    Integer getGroupIdByGroupNameAndUserId(String groupName, int userId);

}
