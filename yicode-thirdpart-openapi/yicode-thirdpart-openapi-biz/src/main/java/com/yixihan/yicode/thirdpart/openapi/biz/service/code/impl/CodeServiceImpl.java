package com.yixihan.yicode.thirdpart.openapi.biz.service.code.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.code.CodeFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 图片验证码 服务实现类
 *
 * @author yixihan
 * @date 2022/11/23 14:55
 */
@Slf4j
@Service
public class CodeServiceImpl implements CodeService {

    @Resource
    private CodeFeignClient codeFeignClient;

    @Override
    public void createCode(HttpServletResponse response, String uuid) {

    }

    @Override
    public CommonVO<Boolean> validateCode(CodeValidateReq req) {
        return null;
    }
}
