package com.yixihan.yicode.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页工具类
 *
 * @author yixihan
 * @date 2022-09-29-14:48
 */
public class PageUtil {
    
    private PageUtil() {
    }

    public static <K, V> Page<V> convertTo(Page<K> page, Function<? super K, ? extends V> mapper) {
        Page<V> newPage = new Page<> ();
        BeanUtil.copyProperties (page, newPage, getCopyOption ());
        newPage.setRecords (page.getRecords ().stream ().map (mapper).collect (Collectors.toList ()));
        return newPage;
    }

    public static <K, V> Page<V> convertTo(IPage<K> page, Function<? super K, ? extends V> mapper) {
        Page<V> newPage = new Page<> ();
        BeanUtil.copyProperties (page, newPage, getCopyOption ());
        newPage.setRecords (page.getRecords ().stream ().map (mapper).collect (Collectors.toList ()));
        return newPage;
    }

    /**
     * 将 mybatisPlus 的 Page 转换为 PageDtoResult
     *
     */
    public static <T> PageDtoResult<T> pageToPageDtoResult(IPage<T> page) {
        return new PageDtoResult<> (page.getCurrent (), page.getTotal (), page.getSize (), page.getPages (), page.getRecords ());
    }


    /**
     * 将 mybatisPlus 的 Page 转换为 PageDtoResult
     *
     * @param convert 实体转换函数
     */
    public static <T, R> PageDtoResult<R> pageToPageDtoResult(IPage<T> page, Function<T, R> convert) {
        List<T> records = page.getRecords ();
        List<R> rList = records == null ? null : records.stream ().map (convert).collect (Collectors.toList ());
        return new PageDtoResult<> (page.getCurrent (), page.getTotal (), page.getSize (), page.getPages (), rList);
    }
    
    /**
     * 自定义分页请求参数转为 mybatis plus 分页请求参数
     *
     * @param <T> 目标类
     * @param req 分页请求参数
     * @return {@code Page<T>}
     */
    public static <T> Page<T> toPage (PageDtoReq req) {
        return new Page<> (req.getPage (), req.getPageSize (), req.getSearchCount ());
    }

    private static CopyOptions getCopyOption() {
        CopyOptions copyOptions = new CopyOptions ();
        copyOptions.ignoreError ();
        copyOptions.ignoreNullValue ();
        copyOptions.setIgnoreProperties ("records");
        return copyOptions;
    }
}
