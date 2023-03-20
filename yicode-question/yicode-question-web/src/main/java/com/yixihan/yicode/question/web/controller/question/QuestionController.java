package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.admin.BrokenDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.admin.CommitDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCountDtoResult;
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
    public ApiResult<QuestionDetailDtoResult> addQuestion(ModifyQuestionDtoReq dtoReq) {
        return ApiResult.create (service.addQuestion (dtoReq));
    }
    
    @Override
    public ApiResult<QuestionDetailDtoResult> modifyQuestion(ModifyQuestionDtoReq dtoReq) {
        return ApiResult.create (service.modifyQuestion (dtoReq));
    }
    
    @Override
    public void delQuestion(List<Long> questionIdList) {
        service.delQuestion (questionIdList);
    }
    
    @Override
    public void likeQuestion(LikeDtoReq dtoReq) {
        service.likeQuestion (dtoReq);
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
    public ApiResult<Boolean> verifyQuestion(Long questionId) {
        return ApiResult.create (service.verifyQuestion (questionId));
    }
    
    @Override
    public ApiResult<Map<Long, String>> questionName(List<Long> questionIdList) {
        return ApiResult.create (service.questionName (questionIdList));
    }
    
    @Override
    public ApiResult<Map<String, BrokenDataDtoResult>> brokenData(AdminDataDtoReq dtoReq) {
        return ApiResult.create (service.brokenData (dtoReq));
    }
    
    @Override
    public ApiResult<CommitDataDtoResult> commitData(AdminDataDtoReq dtoReq) {
        return ApiResult.create (service.commitData (dtoReq));
    }
    
    @Override
    public ApiResult<QuestionCountDtoResult> questionCount() {
        return ApiResult.create (service.questionCount ());
    }
}
