package com.todoDesign.mapper;

import com.todoDesign.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 用户 Mapper 接口
 * <p>
 * 这是一个用于操作用户的 Mapper 接口。
 * 通过继承 BaseMapper<User>，可以使用 MyBatis-Plus 提供的通用 CRUD 方法。
 *
 * @author Mory
 * @since 2023-08-10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByUsername(String userName);

}
