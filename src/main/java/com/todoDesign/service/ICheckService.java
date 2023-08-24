package com.todoDesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.todoDesign.dto.Money;
import com.todoDesign.entity.Check;

/**
 * @author Mory
 * &date  2023/8/24 9:56
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
public interface ICheckService extends IService<Check> {

    Check subscribeCheck(Money money, int userId);

    Check getCheckByCheckId(int checkId);
}
