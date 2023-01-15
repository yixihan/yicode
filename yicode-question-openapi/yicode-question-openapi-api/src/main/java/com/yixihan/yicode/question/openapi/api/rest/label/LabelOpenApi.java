package com.yixihan.yicode.question.openapi.api.rest.label;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.label.AddLabelReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 标签 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "标签 OpenApi")
@RequestMapping("/open/label")
public interface LabelOpenApi {
    
    @ApiOperation("添加标签")
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addLabel (@RequestBody AddLabelReq req);
    
        @ApiOperation ("删除标签")
    @DeleteMapping(value = "/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delLabel (@RequestBody List<Long> labelIdList);
    
    @ApiOperation ("标签明细")
    @PostMapping(value = "/detail", produces = "application/json")
    JsonResponse<List<LabelVO>> labelDetail (@RequestBody List<Long> labelIdList);

}
