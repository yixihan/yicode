package com.yixihan.yicode.question.openapi.api.rest.question;

import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCountVO;
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
    @RoleAccess(value = {RoleEnums.ADMIN, RoleEnums.SUPER_ADMIN})
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<QuestionDetailVO> addQuestion(@RequestBody ModifyQuestionReq req);
    
    @ApiOperation("修改问题")
    @RoleAccess(value = {RoleEnums.ADMIN, RoleEnums.SUPER_ADMIN})
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<QuestionDetailVO> modifyQuestion(@RequestBody ModifyQuestionReq req);
    
    @ApiOperation("删除问题")
    @RoleAccess(value = {RoleEnums.ADMIN, RoleEnums.SUPER_ADMIN})
    @DeleteMapping(value = "/del", produces = "application/json")
    void delQuestion(@RequestBody List<Long> questionIdList);
    
    @ApiOperation("点赞问题")
    @PostMapping(value = "/like", produces = "application/json")
    void likeQuestion(@RequestBody LikeReq req);
    
    @ApiOperation("收藏问题")
    @PostMapping(value = "/collection", produces = "application/json")
    void collectionQuestion(@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("取消收藏问题")
    @DeleteMapping(value = "/collection/cancel", produces = "application/json")
    void cancelCollectionQuestion(@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("修改问题标签")
    @RoleAccess(value = {RoleEnums.ADMIN, RoleEnums.SUPER_ADMIN})
    @PostMapping(value = "/label/modify", produces = "application/json")
    JsonResponse<List<LabelVO>> modifyQuestionLabel(@RequestBody ModifyLabelQuestionReq req);
    
    @ApiOperation("问题明细")
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<QuestionDetailVO> questionDetail(@RequestParam Long questionId);
    
    @ApiOperation("搜索问题")
    @PostMapping(value = "/query", produces = "application/json")
    JsonResponse<PageVO<QuestionVO>> queryQuestion(@RequestBody QueryQuestionReq req);
    
    @ApiOperation("随机一题")
    @GetMapping(value = "/detail/random", produces = "application/json")
    JsonResponse<QuestionDetailVO> randomQuestion();
    
    @ApiOperation("获取题目数量")
    @GetMapping(value = "/all/count", produces = "application/json")
    JsonResponse<QuestionCountVO> questionCount();

}
