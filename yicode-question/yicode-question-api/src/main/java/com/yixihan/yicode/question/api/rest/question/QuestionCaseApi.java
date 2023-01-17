package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
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
    ApiResult<CommonDtoResult<Boolean>> addQuestionCase (@RequestBody ModifyQuestionCaseDtoReq dtoReq);
    
    @ApiOperation("修改测试用例")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> modifyQuestionCase (@RequestBody ModifyQuestionCaseDtoReq dtoReq);
    
    @ApiOperation("删除测试用例")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delQuestionCase (@RequestBody Long id);
    
    @ApiOperation("获取测试用例")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<QuestionCaseDtoResult>> allQuestionCase (@RequestBody Long questionId);
    
    
    @ApiOperation("校验测试用例")
    @PostMapping(value = "/verify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifyQuestionCase (@RequestBody Long id);
}
