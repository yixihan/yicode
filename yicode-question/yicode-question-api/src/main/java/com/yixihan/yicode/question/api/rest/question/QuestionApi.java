package com.yixihan.yicode.question.api.rest.question;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
