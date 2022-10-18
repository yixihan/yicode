package com.yixihan.yicode.common.util;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单复制类. 仅支持无特殊构造函数的 copy
 * @author yixihan
 * @date 2022-09-29-14:20
 */
public class CopyUtils {

    /**
     * 单值拷贝
     *
     * @param targetClazz 目标类
     * @param sourceData  源数据
     * @param <T>         目标泛型
     * @param <S>         源泛型
     * @return 拷贝对象
     */
    public static <T, S> T copySingle(Class<T> targetClazz, S sourceData) {
        if (sourceData == null) {
            return null;
        }
        // 复制器 默认带缓存. 所以不用存起来
        BeanCopier beanCopier = BeanCopier.create(sourceData.getClass(), targetClazz, false);
        return copySingle(targetClazz, sourceData, beanCopier);
    }

    /**
     * 单值复制器
     *
     * @param targetClazz 目标类
     * @param sourceData  源数据
     * @param beanCopier  复制器
     * @param <T>         目标泛型
     * @param <S>         源泛型
     * @return 复制结果
     */
    private static <T, S> T copySingle(Class<T> targetClazz, S sourceData, BeanCopier beanCopier) {
        T temp;
        try {
            temp = targetClazz.getDeclaredConstructor().newInstance();
            beanCopier.copy(sourceData, temp, null);
        } catch (Exception e) {
            return null;
        }
        return temp;
    }

    /**
     * 列表拷贝
     *
     * @param targetClazz    目标类
     * @param sourceDataList 源数据
     * @param <T>            目标泛型
     * @param <S>            源泛型
     * @return 拷贝列表
     */
    public static <T, S> List<T> copyMulti(Class<T> targetClazz, List<S> sourceDataList) {

        if (CollectionUtils.isEmpty(sourceDataList)) {
            return new ArrayList<> ();
        }
        List<T> result = new ArrayList<>(sourceDataList.size());
        BeanCopier beanCopier = BeanCopier.create(sourceDataList.get(0).getClass(), targetClazz, false);

        for (S temp : sourceDataList) {
            result.add(copySingle(targetClazz, temp, beanCopier));
        }
        return result;
    }
}
