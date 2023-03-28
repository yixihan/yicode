package com.yixihan.yicode.question.openapi.api.rest.question;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCaseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 问题测试用例管理 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "问题测试用例管理 OpenApi")
@RequestMapping("/open/question/case")
public interface QuestionCaseOpenApi {
    
    @ApiOperation("添加测试用例")
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<QuestionCaseVO> addQuestionCase (@RequestBody ModifyQuestionCaseReq req);
    
    @ApiOperation("修改测试用例")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<QuestionCaseVO> modifyQuestionCase (@RequestBody ModifyQuestionCaseReq req);
    
    @ApiOperation("删除测试用例")
    @DeleteMapping(value = "/del", produces = "application/json")
    void delQuestionCase (@RequestParam Long id);
    
    @ApiOperation("获取测试用例")
    @PostMapping(value = "/detail", produces = "application/json")
    JsonResponse<PageVO<QuestionCaseVO>> allQuestionCase (@RequestParam QueryQuestionCaseReq req);

}
