package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CodeRateDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;

import java.util.List;

/**
 * <p>
 * 问题答案表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface QuestionAnswerService extends IService<QuestionAnswer> {
    
    /**
     * 添加问题答案
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionAnswerDtoResult}
     */
    QuestionAnswerDtoResult addQuestionAnswer(AddQuestionAnswerDtoReq dtoReq);
    
    /**
     * 获取问题答案详情
     *
     * @param questionAnswerId 问题答案 ID
     * @return {@link QuestionAnswerDtoResult}
     */
    QuestionAnswerDtoResult questionAnswerDetail(Long questionAnswerId);
    
    /**
     * 获取单个问题提交记录
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionAnswerDtoResult}
     */
    PageDtoResult<QuestionAnswerDtoResult> queryQuestionAnswer(QuestionAnswerDtoReq dtoReq);
    
    /**
     * 获取用户问题提交记录
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionAnswerDtoResult}
     */
    PageDtoResult<QuestionAnswerDtoResult> queryUserQuestionAnswer(UserQuestionAnswerDtoReq dtoReq);
    
    /**
     * 获取用户提交代码次数记录
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionAnswerDtoResult}
     */
    List<CommitRecordDtoResult> codeCommitCount(CodeCommitCountDtoReq dtoReq);
    
    /**
     * 获取用户做题进度
     *
     * @param userId 用户 ID
     * @return {@link CodeRateDtoResult}
     */
    CodeRateDtoResult codeRate(Long userId);
}
