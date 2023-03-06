package com.yixihan.yicode.question.openapi.api.rest.admin;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.AdminDataReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.BrokenDataVO;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.CommitDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理中心 OpenApi
 *
 * @author yixihan
 * @date 2023/3/3 22:03
 */
@Api(tags = "管理中心 OpenApi")
@RequestMapping("/open/admin")
public interface AdminOpenApi {
    
    @ApiOperation("管理中心-查看网址数据")
    @GetMapping(value = "/broken/data", produces = "application/json")
    JsonResponse<BrokenDataVO> brokenData (@RequestBody AdminDataReq req);
    
    @ApiOperation("管理中心-代码提交数据")
    @GetMapping(value = "/commit/data", produces = "application/json")
    JsonResponse<CommitDataVO> commitData (@RequestBody AdminDataReq req);
}
