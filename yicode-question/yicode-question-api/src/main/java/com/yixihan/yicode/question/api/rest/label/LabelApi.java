package com.yixihan.yicode.question.api.rest.label;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.AddLabelDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 标签 api
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "标签 api")
@RequestMapping("/label")
public interface LabelApi {
    
    @ApiOperation("添加标签")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<LabelDtoResult> addLabel(@RequestBody AddLabelDtoReq dtoReq);
    
    @ApiOperation("删除标签")
    @PostMapping(value = "/del", produces = "application/json")
    void delLabel(@RequestBody List<Long> labelIdList);
    
    @ApiOperation("标签明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<LabelDtoResult>> labelDetail(@RequestBody List<Long> labelIdList);
    
    @ApiOperation("获取全部标签")
    @PostMapping(value = "/all", produces = "application/json")
    ApiResult<PageDtoResult<LabelDtoResult>> allLabel(@RequestBody PageDtoReq dtoReq);
    
    
    @ApiOperation("校验标签")
    @PostMapping(value = "/verify", produces = "application/json")
    ApiResult<Boolean> verifyLabel(@RequestBody Long labelId);
}
