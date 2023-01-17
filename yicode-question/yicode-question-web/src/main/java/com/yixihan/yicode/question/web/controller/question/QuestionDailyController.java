package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDailyDtoResult;
import com.yixihan.yicode.question.api.rest.question.QuestionDailyApi;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public ApiResult<List<QuestionDailyDtoResult>> dailyQuestionDetail(Date month) {
        return ApiResult.create (service.dailyQuestionDetail (month));
    }
}
