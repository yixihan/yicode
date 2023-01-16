package com.yixihan.yicode.question.api.rest.label;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelUserDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 标签-用户 api
 *
 * @author yixihan
 * @date 2023/1/11 10:16
 */
@Api(tags = "标签-用户 api")
@RequestMapping("/label/user")
public interface LabelUserApi {
    
    @ApiOperation("添加用户标签")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addUserLabel (@RequestBody ModifyLabelUserDtoReq dtoReq);
    
    @ApiOperation ("删除用户标签")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delUserLabel (@RequestBody ModifyLabelUserDtoReq dtoReq);
    
    @ApiOperation ("用户标签明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<LabelDtoResult>> userLabelDetail (@RequestBody Long userId);
}
