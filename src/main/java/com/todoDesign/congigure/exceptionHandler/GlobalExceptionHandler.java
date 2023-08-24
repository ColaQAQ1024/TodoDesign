package com.todoDesign.congigure.exceptionHandler;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Mory
 * &date  2023/8/16 11:51
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //全局异常拦截 -> 转换为Json格式
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}
