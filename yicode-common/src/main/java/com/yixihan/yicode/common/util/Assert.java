package com.yixihan.yicode.common.util;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;

/**
 * 断言工具类
 *
 * @author yixihan
 * @date 2023/2/15 17:21
 */
public class Assert {
    
    private Assert() {
    }
    
    /**
     * 断言是否为真，如果为 {@code false} 抛出给定的异常<br>
     *
     * @param expression 布尔值
     * @param enums      指定断言不通过时抛出的异常信息 {@link BizCodeEnum}
     */
    public static void isTrue(Boolean expression, BizCodeEnum enums) {
        isTrue (expression, new BizException (enums));
    }
    
    /**
     * 断言是否为真，如果为 {@code false} 抛出给定的异常<br>
     *
     * @param expression 布尔值
     * @param e          指定断言不通过时抛出的异常
     */
    public static void isTrue(Boolean expression, BizException e) {
        if (Boolean.FALSE.equals (expression)) {
            throw e;
        }
    }
    
    /**
     * 断言是否为真，如果为 {@code false} 抛出{@code BizCodeEnum.PARAMS_VALID_ERR}<br>
     *
     * @param expression 布尔值
     */
    public static void isTrue(Boolean expression) {
        isTrue (expression, BizCodeEnum.PARAMS_VALID_ERR);
    }
    
    /**
     * 断言是否为假，如果为 {@code true} 抛出给定的异常<br>
     *
     * @param expression 布尔值
     * @param enums      指定断言不通过时抛出的异常信息 {@link BizCodeEnum}
     */
    public static void isFalse(Boolean expression, BizCodeEnum enums) {
        isFalse (expression, new BizException (enums));
    }
    
    /**
     * 断言是否为假，如果为 {@code true} 抛出给定的异常<br>
     *
     * @param expression 布尔值
     * @param e          指定断言不通过时抛出的异常
     */
    public static void isFalse(Boolean expression, BizException e) {
        if (Boolean.TRUE.equals (expression)) {
            throw e;
        }
    }
    
    /**
     * 断言是否为假，如果为 {@code true} 抛出{@code BizCodeEnum.PARAMS_VALID_ERR}<br>
     *
     * @param expression 布尔值
     */
    public static void isFalse(Boolean expression) {
        isFalse (expression, BizCodeEnum.PARAMS_VALID_ERR);
    }
    
    /**
     * 断言对象是否不为 {@code  null}，如果为 {@code null} 抛出给定的异常<br>
     *
     * @param obj   对象
     * @param enums 指定断言不通过时抛出的异常信息 {@link BizCodeEnum}
     */
    public static void notNull(Object obj, BizCodeEnum enums) {
        notNull (obj, new BizException (enums));
    }
    
    /**
     * 断言对象是否不为 {@code  null}，如果为 {@code null} 抛出给定的异常<br>
     *
     * @param obj 对象
     * @param e   指定断言不通过时抛出的异常
     */
    public static void notNull(Object obj, BizException e) {
        if (obj == null) {
            throw e;
        }
    }
    
    /**
     * 断言对象是否不为 {@code  null}，如果为 {@code null} 抛出 {@code BizCodeEnum.PARAMS_VALID_ERR}<br>
     *
     * @param obj 对象
     */
    public static void notNull(Object obj) {
        notNull (obj, BizCodeEnum.PARAMS_VALID_ERR);
    }
    
    /**
     * 断言对象是否为 {@code  null}，如果不为 {@code null} 抛出给定的异常<br>
     *
     * @param obj 对象
     * @param e 指定断言不通过时抛出的异常
     */
    public static void isNull(Object obj, BizException e) {
        if (obj == null) {
            throw e;
        }
    }
    
    /**
     * 断言对象是否为 {@code  null}，如果不为 {@code null} 抛出给定的异常<br>
     *
     * @param obj   对象
     * @param enums 指定断言不通过时抛出的异常信息 {@link BizCodeEnum}
     */
    public static void isNull(Object obj, BizCodeEnum enums) {
        isNull (obj, new BizException (enums));
    }
    
    /**
     * 断言对象是否为 {@code  null}，如果不为 {@code null} 抛出 {@code BizCodeEnum.PARAMS_VALID_ERR}<br>
     *
     * @param obj   对象
     */
    public static void isNull(Object obj) {
        isNull (obj, BizCodeEnum.PARAMS_VALID_ERR);
    }
}
