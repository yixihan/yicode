package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.api.rest.question.QuestionCaseApi;
import com.yixihan.yicode.question.biz.service.question.QuestionCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 问题测试用例表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class QuestionCaseController implements QuestionCaseApi {
    
    @Resource
    private QuestionCaseService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        return ApiResult.create (service.addQuestionCase (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> modifyQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        return ApiResult.create (service.modifyQuestionCase (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delQuestionCase(Long id) {
        return ApiResult.create (service.delQuestionCase (id));
    }
    
    @Override
    public ApiResult<List<QuestionCaseDtoResult>> allQuestionCase(Long questionId) {
        return ApiResult.create (service.allQuestionCase (questionId));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyQuestionCase(Long id) {
        return ApiResult.create (service.verifyQuestionCase (id));
    }
}
