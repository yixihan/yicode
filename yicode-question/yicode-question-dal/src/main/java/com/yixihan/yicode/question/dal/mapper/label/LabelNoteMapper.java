package com.yixihan.yicode.question.dal.mapper.label;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.dal.pojo.label.LabelNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 题解标签表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface LabelNoteMapper extends BaseMapper<LabelNote> {
    
    /**
     * 获取所有题解的标签
     *
     * @param labelName 标签名
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> allNoteLabel(@Param ("labelName") String labelName);
}
