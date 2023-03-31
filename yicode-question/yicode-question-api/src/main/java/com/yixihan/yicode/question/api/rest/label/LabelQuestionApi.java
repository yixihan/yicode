package com.yixihan.yicode.question.api.rest.label;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 标签-问题 api
 *
 * @author yixihan
 * @date 2023/1/11 10:16
 */
@Api(tags = "标签-问题 api")
@RequestMapping("/label/question")
public interface LabelQuestionApi {
    
    @ApiOperation ("修改问题标签")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<List<LabelDtoResult>> modifyQuestionLabel (@RequestBody ModifyLabelQuestionDtoReq dtoReq);
    
    @ApiOperation ("问题标签明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<LabelDtoResult>> questionLabelDetail (@RequestBody Long questionId);
    
    @ApiOperation ("获取所有问题的标签")
    @PostMapping(value = "/all", produces = "application/json")
    ApiResult<List<LabelDtoResult>> allQuestionLabel (@RequestBody(required = false) String labelName);
}
