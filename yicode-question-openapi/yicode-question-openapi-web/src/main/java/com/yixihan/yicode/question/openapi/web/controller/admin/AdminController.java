package com.yixihan.yicode.question.openapi.web.controller.admin;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.admin.AdminOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.AdminDataReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.BrokenDataVO;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.CommitDataVO;
import com.yixihan.yicode.question.openapi.biz.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理中心 前端控制器
 *
 * @author yixihan
 * @date 2023/3/3 22:07
 */
@Slf4j
@RestController
public class AdminController implements AdminOpenApi {
    
    @Resource
    private AdminService service;
    
    @Override
    public JsonResponse<List<BrokenDataVO>> brokenData(AdminDataReq req) {
        return JsonResponse.ok (service.brokenData (req));
    }
    
    @Override
    public JsonResponse<CommitDataVO> commitData(AdminDataReq req) {
        return JsonResponse.ok (service.commitData (req));
    }
}
