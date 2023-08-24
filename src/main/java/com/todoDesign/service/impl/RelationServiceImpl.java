package com.todoDesign.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.dto.Money;
import com.todoDesign.entity.Relation;
import com.todoDesign.mapper.RelationMapper;
import com.todoDesign.service.IRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<String> subscribeTodo(Money money) {
        if (money.getAmount() > 0){
            int userId = (Integer) StpUtil.getSession().get("userId");
            Relation relation = Optional.ofNullable(
                    this.lambdaQuery()
                            .eq(Relation::getUserId,userId)
                            .one())
                    .orElseThrow();
            relation.setRoleId(3);
            this.updateById(relation);
            return ResponseEntity.ok("订阅成功");
        }
        return ResponseEntity.ok("订阅一下吧(*╹▽╹*)！很便宜的喵");
    }
}
