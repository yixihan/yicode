package com.yixihan.yicode.question.api.rest.label;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
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

    @ApiOperation ("添加标签")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addLabel (@RequestBody String labelName);
    
    @ApiOperation ("删除标签")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delLabel (@RequestBody Long labelId);
    
    @ApiOperation ("标签明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<LabelDtoResult>> labelDetail (@RequestBody List<Long> labelIdList);
}
