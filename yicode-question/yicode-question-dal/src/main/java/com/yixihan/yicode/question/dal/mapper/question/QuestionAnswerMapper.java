package com.yixihan.yicode.question.dal.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 问题答案表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {

}
