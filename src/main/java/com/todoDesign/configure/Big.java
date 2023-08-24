package com.todoDesign.configure;

import jakarta.annotation.Nullable;
import lombok.Data;

/**
 * @author Mory
 * &date  2023/8/24 11:24
 * &introduce 烽火兴旺，凡我喵喵教，喵喵喵！
 */
@Data
public class  Big<T> {

    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;

    private boolean pass;
    private String message;

    //对应的传入实体
    private T data;

    public static <T> Big<T> ac(@Nullable T data) {
        Big<T> big = new Big<>();
        big.setPass(SUCCESS);
        big.setData(data);
        return big;
    }

    public static <T> Big<T> ac(@Nullable T data, String message) {
        Big<T> big = new Big<>();
        big.setPass(SUCCESS);
        big.setMessage(message);
        big.setData(data);
        return big;
    }

    public static <T> Big<T> re(@Nullable T data) {
        Big<T> big = new Big<>();
        big.setPass(FAILED);
        return big;
    }

}
