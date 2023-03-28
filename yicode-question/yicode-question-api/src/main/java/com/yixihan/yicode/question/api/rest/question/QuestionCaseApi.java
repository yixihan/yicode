package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 问题-测试用例 api
 *
 * @author yixihan
 * @date 2023/1/11 10:17
 */
@Api(tags = "问题-测试用例 api")
@RequestMapping("/question/case")
public interface QuestionCaseApi {
    
    @ApiOperation("添加测试用例")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<QuestionCaseDtoResult> addQuestionCase (@RequestBody ModifyQuestionCaseDtoReq dtoReq);
    
    @ApiOperation("修改测试用例")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<QuestionCaseDtoResult> modifyQuestionCase (@RequestBody ModifyQuestionCaseDtoReq dtoReq);
    
    @ApiOperation("删除测试用例")
    @PostMapping(value = "/del", produces = "application/json")
    void delQuestionCase (@RequestBody Long id);
    
    @ApiOperation("获取测试用例")
    @PostMapping(value = "/detail/list", produces = "application/json")
    ApiResult<List<QuestionCaseDtoResult>> allQuestionCaseList(@RequestBody Long questionId);
    
    @ApiOperation("获取测试用例")
    @PostMapping(value = "/detail/page", produces = "application/json")
    ApiResult<PageDtoResult<QuestionCaseDtoResult>> allQuestionCasePage (@RequestBody QueryQuestionCaseDtoReq dtoReq);
    
    @ApiOperation("校验测试用例")
    @PostMapping(value = "/verify", produces = "application/json")
    ApiResult<Boolean> verifyQuestionCase (@RequestBody Long id);
}
