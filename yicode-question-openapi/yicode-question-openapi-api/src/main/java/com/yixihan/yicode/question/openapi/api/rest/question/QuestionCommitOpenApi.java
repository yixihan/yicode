package com.yixihan.yicode.question.openapi.api.rest.question;


import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.question.AddQuestionNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserQuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRateVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CommitRecordVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionAnswerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提交答案 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "提交答案 OpenApi")
@RequestMapping("/open/question/commit")
public interface QuestionCommitOpenApi {
    
    @ApiOperation("测试代码")
    @PostMapping(value = "/test", produces = "application/json")
    void test(@RequestBody CodeReq req);
    
    @ApiOperation("提交代码")
    @PostMapping(value = "/commit", produces = "application/json")
    void commit(@RequestBody CodeReq req);
    
    @ApiOperation("备注问题提交记录")
    @PostMapping(value = "/add/note", produces = "application/json")
    JsonResponse<QuestionAnswerVO> addQuestionAnswerNote(@RequestBody AddQuestionNoteReq req);
    
    @ApiOperation("获取单个问题提交记录")
    @PostMapping(value = "/result/question", produces = "application/json")
    JsonResponse<PageVO<QuestionAnswerVO>> queryQuestionAnswer(@RequestBody QuestionAnswerReq req);
    
    @ApiOperation("获取用户问题提交记录")
    @PostMapping(value = "/result/user", produces = "application/json")
    JsonResponse<PageVO<QuestionAnswerVO>> queryUserQuestionAnswer(@RequestBody UserQuestionAnswerReq req);
    
    @ApiOperation("获取用户提交代码次数记录 (yyyy-MM)")
    @GetMapping(value = "/result/count", produces = "application/json")
    JsonResponse<List<CommitRecordVO>> codeCommitCount(@RequestParam("month") String month,
                                                       @RequestParam(value = "userId", required = false) Long userId);
    
    @ApiOperation("获取用户做题进度")
    @GetMapping(value = "/rate", produces = "application/json")
    JsonResponse<CodeRateVO> codeRate(@RequestParam(value = "userId", required = false) Long userId);
}
