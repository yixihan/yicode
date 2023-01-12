package com.yixihan.yicode.question.web.controller.question;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.MonthUserDailyQuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.AddUserDailyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserDailyQuestionDtoResult;
import com.yixihan.yicode.question.api.reset.question.QuestionDailyUserApi;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户每日一题表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class QuestionDailyUserController implements QuestionDailyUserApi {
    
    @Resource
    private QuestionDailyUserService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addUserDailyQuestion(AddUserDailyQuestionDtoReq dtoReq) {
        return ApiResult.create (service.addUserDailyQuestion (dtoReq));
    }
    
    @Override
    public ApiResult<List<UserDailyQuestionDtoResult>> monthUserDailyQuestionDetail(MonthUserDailyQuestionDetailDtoReq dtoReq) {
        return ApiResult.create (service.monthUserDailyQuestionDetail (dtoReq));
    }
}