package com.yixihan.yicode.thirdpart.biz.service.code.impl;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.api.constant.code.CodeConstant;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.biz.service.code.CodeService;
import com.yixihan.yicode.thirdpart.biz.service.code.PhotoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 图片验证码 服务实现类
 *
 * @author yixihan
 * @date 2022/12/26 14:36
 */
@Slf4j
@Service
public class PhotoCodeServiceImpl implements PhotoCodeService {
    
    @Resource
    private CodeService codeService;
    
    @Resource
    private CodeConstant codeConstant;
    
    @Override
    public void createCode(String code, String uuid) {
        String keyName = String.format (codeConstant.getCommonKey (), uuid);
        // 存入 redis
        codeService.addRedis (keyName, code);
    }
    
    @Override
    public CommonDtoResult<Boolean> validateCode(CodeValidateDtoReq dtoReq) {
        String keyName = String.format (codeConstant.getCommonKey (), dtoReq.getUuid ());
        return codeService.validate (keyName, dtoReq.getCode ());
    }
}
