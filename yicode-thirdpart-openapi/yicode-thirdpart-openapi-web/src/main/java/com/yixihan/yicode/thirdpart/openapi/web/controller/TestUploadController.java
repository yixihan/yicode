package com.yixihan.yicode.thirdpart.openapi.web.controller;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.openapi.api.reset.TestUploadOpenApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.TestUpLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author yixihan
 * @date 2022-10-24-15:35
 */
@Slf4j
@RestController
public class TestUploadController implements TestUploadOpenApi {

    @Resource
    private TestUpLoadService testUpLoadService;

    @Override
    public JsonResponse<String> testUploadFile(MultipartFile file) {
        return JsonResponse.ok (testUpLoadService.uploadFile (file));
    }
}
