package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDailyDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 问题-每日一题 api
 *
 * @author yixihan
 * @date 2023/1/11 10:18
 */
@Api(tags = "问题-每日一题 api")
@RequestMapping("/question/daily")
public interface QuestionDailyApi {
    
    @ApiOperation("获取指定月每日一题详情")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<QuestionDailyDtoResult>> dailyQuestionDetail (@RequestBody Date month);
}
