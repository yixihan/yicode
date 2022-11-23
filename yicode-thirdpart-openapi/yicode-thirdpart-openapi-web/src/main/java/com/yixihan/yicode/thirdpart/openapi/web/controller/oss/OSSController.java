package com.yixihan.yicode.thirdpart.openapi.web.controller.oss;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.reset.oss.OSSOpenApi;
import com.yixihan.yicode.thirdpart.open.api.vo.request.oss.OSSUploadReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * oss 模块 前端控制器
 *
 * @author yixihan
 * @date 2022/11/23 14:42
 */
@Slf4j
@RestController
public class OSSController implements OSSOpenApi {

    @Resource
    private OSSService ossService;

    @Override
    public JsonResponse<CommonVO<String>> uploadFile(OSSUploadReq req) {
        return JsonResponse.ok (ossService.uploadFile (req));
    }

    @Override
    public JsonResponse<Map<String, String>> policy() {
        return JsonResponse.ok (ossService.policy ());
    }
}
