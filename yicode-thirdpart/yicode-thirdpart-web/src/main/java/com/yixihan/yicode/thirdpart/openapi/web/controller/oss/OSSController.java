package com.yixihan.yicode.thirdpart.openapi.web.controller.oss;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.openapi.api.reset.oss.OSSApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * oss 前端控制器
 *
 * @author yixihan
 * @date 2022-10-24-15:35
 */
@Slf4j
@RestController
public class OSSController implements OSSApi {

    @Resource
    private OSSService ossService;

    @Override
    public JsonResponse<String> testUploadFile(MultipartFile file) {
        return JsonResponse.ok (ossService.uploadFile (file));
    }
}
