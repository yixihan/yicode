package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;

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
     */
    CommonDtoResult<Boolean> addQuestionAnswer(AddQuestionAnswerDtoReq dtoReq);
    
    /**
     * 获取问题答案详情
     *
     * @param questionAnswerId 问题答案 ID
     */
    QuestionAnswerDtoResult questionAnswerDetail(Long questionAnswerId);
    
    /**
     * 获取问题答案
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<QuestionAnswerDtoResult> queryQuestionAnswer(QueryQuestionAnswerDtoReq dtoReq);
}
