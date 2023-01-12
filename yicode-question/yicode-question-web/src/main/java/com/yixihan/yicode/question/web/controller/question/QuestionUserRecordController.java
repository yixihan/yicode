package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserRecordDtoResult;
import com.yixihan.yicode.question.api.reset.question.QuestionUserRecordApi;
import com.yixihan.yicode.question.biz.service.question.QuestionUserRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户题目通过记录表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class QuestionUserRecordController implements QuestionUserRecordApi {
    
    @Resource
    private QuestionUserRecordService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addQuestionUserRecord(AddQuestionUserRecordDtoReq dtoReq) {
        return ApiResult.create (service.addQuestionUserRecord (dtoReq));
    }
    
    @Override
    public ApiResult<UserRecordDtoResult> questionUserRecordDetail(Long id) {
        return ApiResult.create (service.questionUserRecordDetail (id));
    }
    
    @Override
    public ApiResult<PageDtoResult<UserRecordDtoResult>> queryUserRecord(QueryUserRecordDtoReq dtoReq) {
        return ApiResult.create (service.queryQuestionUserRecord (dtoReq));
    }
}
