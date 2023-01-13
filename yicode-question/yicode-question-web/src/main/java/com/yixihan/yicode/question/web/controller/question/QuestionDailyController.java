package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.rest.question.QuestionDailyApi;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 每日一题表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class QuestionDailyController implements QuestionDailyApi {
    
    @Resource
    private QuestionDailyService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addDailyQuestion(Long questionId) {
        return ApiResult.create (service.addDailyQuestion (questionId));
    }
}
