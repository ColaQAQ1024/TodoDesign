package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.configure.Big;
import com.todoDesign.dto.Money;
import com.todoDesign.entity.Relation;

/**
 * 关系 Service 接口
 * <p>
 * 这是一个用于处理关系的 Service 接口。
 * 通过继承 IService<Relation>，可以使用 MyBatis-Plus 提供的通用 CRUD 方法。
 *
 * @author Mory
 * @since 2023-08-18
 */
public interface IRelationService extends IService<Relation> {

    /**
     * 订阅任务服务
     *
     * @param money 金额信息
     * @return 响应实体，包含订阅结果信息
     */
    Big<Object> subscribeTodo(Money money);
}
