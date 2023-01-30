package com.yixihan.yicode.question.dal.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionNumberDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    
    /**
     * 获取用户问题提交记录
     *
     * @param dtoReq 请求参数
     * @param page 分页参数
     */
    Page<QuestionAnswerDtoResult> queryUserQuestionAnswer(@Param ("params") UserQuestionAnswerDtoReq dtoReq,
                                                          @Param ("page") Page<QuestionAnswerDtoResult> page);
    
    /**
     * 获取用户提交代码次数记录
     *
     * @param dtoReq 请求参数
     */
    List<CommitRecordDtoResult> codeCommitCount(@Param ("params") CodeCommitCountDtoReq dtoReq);
    
    /**
     * 获取用户做题进度
     *
     * @param userId 用户 ID
     */
    List<QuestionAnswerDtoResult> codeRate(@Param ("userId") Long userId);
    
    /**
     * 获取题目数量情况
     */
    QuestionNumberDtoResult questionNumber ();
}
