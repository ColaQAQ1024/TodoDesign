package com.todoDesign.mapper;

import com.todoDesign.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mory
 * @since 2023-08-10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(String userName);

}
