package com.yixihan.yicode.question.api.reset.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserRecordDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 问题-用户题目通过记录 api
 *
 * @author yixihan
 * @date 2023/1/11 10:18
 */
@Api(tags = "问题-用户题目通过记录 api")
@RequestMapping("/question/daily/user")
public interface QuestionUserRecordApi {
    
    @ApiOperation("添加用户题目通过记录")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addQuestionUserRecord(@RequestBody AddQuestionUserRecordDtoReq dtoReq);
    
    @ApiOperation("获取用户题目通过记录详情")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<UserRecordDtoResult> questionUserRecordDetail (@RequestBody Long id);
    
    @ApiOperation("获取用户题目通过记录")
    @PostMapping(value = "/all", produces = "application/json")
    ApiResult<PageDtoResult<UserRecordDtoResult>> queryUserRecord (@RequestBody QueryUserRecordDtoReq dtoReq);
}
