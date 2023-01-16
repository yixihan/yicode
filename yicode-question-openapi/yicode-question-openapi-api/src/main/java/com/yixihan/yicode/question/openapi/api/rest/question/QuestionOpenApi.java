package com.yixihan.yicode.question.openapi.api.rest.question;

import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问题 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "问题 OpenApi")
@RequestMapping("/open/question")
public interface QuestionOpenApi {
    
    @ApiOperation("添加问题")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addQuestion(@RequestBody ModifyQuestionReq req);
    
    @ApiOperation("修改问题")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> modifyQuestion(@RequestBody ModifyQuestionReq req);
    
    @ApiOperation("删除问题")
    @RoleAccess(value = RoleEnums.ADMIN)
    @DeleteMapping(value = "/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delQuestion(@RequestBody List<Long> questionIdList);
    
    @ApiOperation("点赞问题")
    @PostMapping(value = "/like", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> likeQuestion(@RequestBody LikeReq req);
    
    @ApiOperation("问题明细")
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<QuestionDetailVO> questionDetail(@RequestParam Long questionId);
    
    @ApiOperation("搜索问题")
    @PostMapping(value = "/query", produces = "application/json")
    JsonResponse<PageVO<QuestionVO>> queryQuestion(@RequestBody QueryQuestionReq req);

}
