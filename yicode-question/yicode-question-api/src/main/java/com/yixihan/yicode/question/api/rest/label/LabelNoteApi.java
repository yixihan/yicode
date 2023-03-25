package com.yixihan.yicode.question.api.rest.label;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 标签-题解 api
 *
 * @author yixihan
 * @date 2023/1/11 10:16
 */
@Api(tags = "标签-题解 api")
@RequestMapping("/label/note")
public interface LabelNoteApi {
    
    @ApiOperation("添加题解标签")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<List<LabelDtoResult>> addNoteLabel (@RequestBody ModifyLabelNoteDtoReq dtoReq);
    
    @ApiOperation ("删除题解标签")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<List<LabelDtoResult>> delNoteLabel (@RequestBody ModifyLabelNoteDtoReq dtoReq);
    
    @ApiOperation ("题解标签明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<LabelDtoResult>> noteLabelDetail (@RequestBody Long noteId);
    
    @ApiOperation ("获取所有题解的标签")
    @PostMapping(value = "/all", produces = "application/json")
    ApiResult<List<LabelDtoResult>> allNoteLabel (@RequestBody(required = false) String labelName);
}
