package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CodeRateDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.api.rest.question.QuestionAnswerApi;
import com.yixihan.yicode.question.biz.service.question.QuestionAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问题答案表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class QuestionAnswerController implements QuestionAnswerApi {
    
    @Resource
    private QuestionAnswerService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addQuestionAnswer(AddQuestionAnswerDtoReq dtoReq) {
        return ApiResult.create (service.addQuestionAnswer (dtoReq));
    }
    
    @Override
    public ApiResult<QuestionAnswerDtoResult> questionAnswerDetail(Long questionAnswerId) {
        return ApiResult.create (service.questionAnswerDetail (questionAnswerId));
    }
    
    @Override
    public ApiResult<PageDtoResult<QuestionAnswerDtoResult>> queryQuestionAnswer(QuestionAnswerDtoReq dtoReq) {
        return ApiResult.create (service.queryQuestionAnswer (dtoReq));
    }
    
    @Override
    public ApiResult<PageDtoResult<QuestionAnswerDtoResult>> queryUserQuestionAnswer(UserQuestionAnswerDtoReq dtoReq) {
        return ApiResult.create (service.queryUserQuestionAnswer (dtoReq));
    }
    
    @Override
    public ApiResult<Map<String, List<CommitRecordDtoResult>>> codeCommitCount(CodeCommitCountDtoReq dtoReq) {
        return ApiResult.create (service.codeCommitCount (dtoReq));
    }
    
    @Override
    public ApiResult<CodeRateDtoResult> codeRate(Long userId) {
        return ApiResult.create (service.codeRate (userId));
    }
}
