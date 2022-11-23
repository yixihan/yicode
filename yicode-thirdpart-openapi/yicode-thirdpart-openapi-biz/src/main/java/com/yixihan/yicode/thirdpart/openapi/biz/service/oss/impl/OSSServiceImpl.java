package com.yixihan.yicode.thirdpart.openapi.biz.service.oss.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.thirdpart.open.api.vo.request.oss.OSSUploadReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.oss.OSSFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * oss 模块 服务实现类
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@Slf4j
@Service
public class OSSServiceImpl implements OSSService {

    @Resource
    private OSSFeignClient ossFeignClient;

    @Override
    public CommonVO<String> uploadFile(OSSUploadReq req) {
        return null;
    }

    @Override
    public Map<String, String> policy() {
        return null;
    }
}
