package com.yixihan.yicode.question.dal.mapper.label;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.dal.pojo.label.LabelQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题标签表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface LabelQuestionMapper extends BaseMapper<LabelQuestion> {
    
    /**
     * 获取所有问题的标签
     *
     * @param labelName 标签名
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> allQuestionLabel(@Param("labelName") String labelName);
}
