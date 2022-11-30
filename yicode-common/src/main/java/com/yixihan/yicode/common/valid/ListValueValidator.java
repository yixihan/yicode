package com.yixihan.yicode.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 此类用于处理 @ListValue注解 标注在 Integer 类型字段
 * implements ConstraintValidator<ListValue, T> 用于处理 @ListValue注解 标注在 T 类型字段
 * <p>
 * 这个 T 需要对应 ListValue注解 定义时 value 字段的类型
 *
 * @author yixihan
 * @date 2022-10-10-13:39
 */
public class ListValueValidator implements ConstraintValidator<ListValue, Integer> {

    /**
     * 可取的范围
     */
    private final Set<Integer> valueSet = new HashSet<> ();


    /**
     * 初始化
     *
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        // 获取到使用注解时指定的允许的数值
        for (int val : constraintAnnotation.value ()) {
            valueSet.add (val);
        }
    }

    /**
     * 是否校验成功
     *
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        // 提供的数值是否是允许的可取值
        return valueSet.contains (integer);
    }
}

