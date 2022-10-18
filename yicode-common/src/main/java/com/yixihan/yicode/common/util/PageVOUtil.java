package com.yixihan.yicode.common.util;

import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * PageVO 转换器
 *
 * @author yixihan
 * @date 2022-09-29-14:32
 */
public class PageVOUtil {

    public static <S, T> void convertPageVO(PageVO<S> source, PageVO<T> target, Function<S, T> convert) {
        List<S> records = source.getRecords ();
        List<T> covertRecords = records == null ? null : records.stream ().map (convert).collect (Collectors.toList ());

        target.setCurrent (source.getCurrent ());
        target.setPages (source.getPages ());
        target.setSize (source.getSize ());
        target.setTotal (source.getTotal ());
        target.setRecords (covertRecords);
    }

    /**
     * PageVO 转换, 适用于实体类转换
     *
     * @param source
     * @param convert
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> PageVO<T> convertPageVO(PageVO<S> source, Function<S, T> convert) {
        PageVO<T> target = new PageVO<T> ();
        convertPageVO (source, target, convert);
        return target;
    }

    /**
     * PageDtoResult 转换为 PageVO
     *
     * @param source
     * @param convert
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> PageVO<T> pageDtoToPageVO(PageDtoResult<S> source, Function<S, T> convert) {
        List<S> records = source.getRecords ();
        List<T> covertRecords = records == null ? null : records.stream ().map (convert).collect (Collectors.toList ());
        return new PageVO<> (source.getCurrent (), source.getTotal (), source.getSize (), source.getPages (), covertRecords);
    }
}
