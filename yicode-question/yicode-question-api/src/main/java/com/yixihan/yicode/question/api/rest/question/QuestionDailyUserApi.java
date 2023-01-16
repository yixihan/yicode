package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.AddUserDailyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.MonthUserDailyQuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserDailyQuestionDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 问题-每日一题-用户做题情况 api
 *
 * @author yixihan
 * @date 2023/1/11 10:18
 */
@Api(tags = "问题-每日一题-用户做题情况 api")
@RequestMapping("/question/daily/user")
public interface QuestionDailyUserApi {

    @ApiOperation ("添加每日一题用户做题情况")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addUserDailyQuestion (@RequestBody AddUserDailyQuestionDtoReq dtoReq);
    
    @ApiOperation ("查询用户每月每日一题情况")
    @PostMapping(value = "/query", produces = "application/json")
    ApiResult<List<UserDailyQuestionDtoResult>> monthUserDailyQuestionDetail (@RequestBody MonthUserDailyQuestionDetailDtoReq dtoReq);
}
