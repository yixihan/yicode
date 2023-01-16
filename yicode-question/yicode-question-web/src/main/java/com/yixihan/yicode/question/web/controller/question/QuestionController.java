package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.api.rest.question.QuestionApi;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问题表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class QuestionController implements QuestionApi {
    
    @Resource
    private QuestionService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addQuestion(ModifyQuestionDtoReq dtoReq) {
        return ApiResult.create (service.addQuestion (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> modifyQuestion(ModifyQuestionDtoReq dtoReq) {
        return ApiResult.create (service.modifyQuestion (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delQuestion(List<Long> questionIdList) {
        return ApiResult.create (service.delQuestion (questionIdList));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> likeQuestion(LikeDtoReq dtoReq) {
        return ApiResult.create (service.likeQuestion (dtoReq));
    }
    
    @Override
    public ApiResult<QuestionDetailDtoResult> questionDetail(Long questionId) {
        return ApiResult.create (service.questionDetail (questionId));
    }
    
    @Override
    public ApiResult<PageDtoResult<QuestionDtoResult>> queryQuestion(QueryQuestionDtoReq dtoReq) {
        return ApiResult.create (service.queryQuestion (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyQuestion(Long questionId) {
        return ApiResult.create (service.verifyQuestion (questionId));
    }
    
    @Override
    public ApiResult<Map<Long, String>> questionName(List<Long> questionIdList) {
        return ApiResult.create (service.questionName (questionIdList));
    }
}
