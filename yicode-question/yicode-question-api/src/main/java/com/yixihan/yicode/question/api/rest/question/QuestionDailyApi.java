package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 问题-每日一题 api
 *
 * @author yixihan
 * @date 2023/1/11 10:18
 */
@Api(tags = "问题-每日一题 api")
@RequestMapping("/question/daily")
public interface QuestionDailyApi {
    
    @ApiOperation ("生成每日一题")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addDailyQuestion (@RequestBody Long questionId);
}
