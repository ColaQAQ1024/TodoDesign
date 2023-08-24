package com.todoDesign.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.todoDesign.dto.Money;
import com.todoDesign.entity.Check;
import com.todoDesign.mapper.CheckMapper;
import com.todoDesign.service.ICheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mory
 * &date  2023/8/24 9:57
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Service
@RequiredArgsConstructor
class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService{

    @Override
    public Check subscribeCheck(Money money, int userId) {
        Check check = new Check();
        check.setUserId(userId);
        check.setCheckStatus(true);
        check.setAmount(money.getAmount());
        check.setMoneyType(money.getType());
        this.save(check);
        return getCheckByCheckId(check.getCheckId());
    }

    @Override
    public Check getCheckByCheckId(int checkId) {
        return this.lambdaQuery().eq(Check::getCheckId,checkId).one();
    }
}
