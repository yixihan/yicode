package com.yixihan.yicode.question.openapi.api.rest.question;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserDailyQuestionDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDailyVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.UserDailyQuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 每日一题 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "每日一题 OpenApi")
@RequestMapping("/open/question/daily")
public interface QuestionDailyOpenApi {
    
    @ApiOperation("获取当月每日一题详情")
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<Map<String, QuestionDailyVO>> dailyQuestionDetail (@RequestParam("month") String month);
    
    @ApiOperation("获取用户连续做每日一题的天数")
    @PostMapping(value = "/count", produces = "application/json")
    JsonResponse<Integer> dailyQuestionCount ();
    
    @ApiOperation("查询用户每月每日一题情况")
    @PostMapping(value = "/query", produces = "application/json")
    JsonResponse<List<UserDailyQuestionVO>> userDailyQuestionDetail (@RequestBody UserDailyQuestionDetailReq req);
}
