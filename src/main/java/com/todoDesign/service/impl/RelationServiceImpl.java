package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.configure.Big;
import com.todoDesign.dto.Money;
import com.todoDesign.entity.Relation;
import com.todoDesign.mapper.RelationMapper;
import com.todoDesign.service.ICheckService;
import com.todoDesign.service.IRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mory
 * &date  2023/8/18 15:12
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Service
@RequiredArgsConstructor
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

    private final ICheckService iCheckService;

    @Override
    public Big<Object> subscribeTodo(Money money) {
        if (money.getAmount() > 0){
            int userId = StpUtil.getLoginIdAsInt();
            Relation relation = Optional.ofNullable(
                            this.lambdaQuery()
                                    .eq(Relation::getUserId,userId)
                                    .one())
                    .orElseThrow();
            relation.setRoleId(3);
            this.updateById(relation);//更新用户角色权限
            return Big.ac(iCheckService.subscribeCheck(money,userId),"订阅成功 订单信息如下:");
        }
        return Big.ac("订阅一下吧(*╹▽╹*)！很便宜的喵");
    }
}
