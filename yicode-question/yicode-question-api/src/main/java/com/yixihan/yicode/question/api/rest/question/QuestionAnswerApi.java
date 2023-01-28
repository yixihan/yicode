package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CodeRateDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 问题答案 api
 *
 * @author yixihan
 * @date 2023/1/11 10:17
 */
@Api(tags = "问题-答案 api")
@RequestMapping("/question/answer")
public interface QuestionAnswerApi {
    
    @ApiOperation("添加问题答案")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addQuestionAnswer(@RequestBody AddQuestionAnswerDtoReq dtoReq);
    
    @ApiOperation("获取问题答案详情")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<QuestionAnswerDtoResult> questionAnswerDetail (@RequestBody Long questionAnswerId);
    
    @ApiOperation("获取单个问题提交记录")
    @PostMapping(value = "/result/question", produces = "application/json")
    ApiResult<PageDtoResult<QuestionAnswerDtoResult>> queryQuestionAnswer (@RequestBody QuestionAnswerDtoReq dtoReq);
    
    @ApiOperation("获取用户问题提交记录")
    @PostMapping(value = "/result/user", produces = "application/json")
    ApiResult<PageDtoResult<QuestionAnswerDtoResult>> queryUserQuestionAnswer (@RequestBody UserQuestionAnswerDtoReq dtoReq);
    
    @ApiOperation("获取用户提交代码次数记录")
    @PostMapping(value = "/result/count", produces = "application/json")
    ApiResult<Map<String, List<CommitRecordDtoResult>>> codeCommitCount(@RequestBody CodeCommitCountDtoReq dtoReq);
    
    @ApiOperation("获取用户做题进度")
    @PostMapping(value = "/rate", produces = "application/json")
    ApiResult<CodeRateDtoResult> codeRate(@RequestBody Long userId);
}
