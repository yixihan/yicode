package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.admin.BrokenDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.admin.CommitDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCountDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 问题 api
 *
 * @author yixihan
 * @date 2023/1/11 10:18
 */
@Api(tags = "问题 api")
@RequestMapping("/question")
public interface QuestionApi {
    
    @ApiOperation("添加问题")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addQuestion(@RequestBody ModifyQuestionDtoReq dtoReq);
    
    @ApiOperation("修改问题")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> modifyQuestion(@RequestBody ModifyQuestionDtoReq dtoReq);
    
    @ApiOperation("删除问题")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delQuestion(@RequestBody List<Long> questionIdList);
    
    @ApiOperation("点赞问题")
    @PostMapping(value = "/like", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> likeQuestion(@RequestBody LikeDtoReq dtoReq);
    
    @ApiOperation("问题明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<QuestionDetailDtoResult> questionDetail(@RequestBody Long questionId);
    
    @ApiOperation("搜索问题")
    @PostMapping(value = "/query", produces = "application/json")
    ApiResult<PageDtoResult<QuestionDtoResult>> queryQuestion(@RequestBody QueryQuestionDtoReq dtoReq);
    
    @ApiOperation("校验问题是否存在")
    @PostMapping(value = "/verify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifyQuestion(@RequestBody Long questionId);
    
    @ApiOperation ("获取问题名字")
    @PostMapping(value = "/detail/name", produces = "application/json")
    ApiResult<Map<Long, String>> questionName(@RequestBody List<Long> questionIdList);
    
    @ApiOperation("管理中心-查看网址数据")
    @PostMapping(value = "/broken/data", produces = "application/json")
    ApiResult<BrokenDataDtoResult> brokenData (@RequestBody AdminDataDtoReq dtoReq);
    
    @ApiOperation("管理中心-代码提交数据")
    @PostMapping(value = "/commit/data", produces = "application/json")
    ApiResult<CommitDataDtoResult> commitData (@RequestBody AdminDataDtoReq dtoReq);
    
    @ApiOperation("获取题目数量")
    @PostMapping(value = "/all/count", produces = "application/json")
    ApiResult<QuestionCountDtoResult> questionCount();
}
