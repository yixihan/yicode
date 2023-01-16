package com.yixihan.yicode.question.dal.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 问题表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    
    /**
     * 搜索问题
     *
     * @param dtoReq 请求参数
     * @param page 分页参数
     */
    Page<QuestionDtoResult> queryQuestion(@Param ("params") QueryQuestionDtoReq dtoReq,
                                          @Param ("page") Page<QuestionDtoResult> page);

}
