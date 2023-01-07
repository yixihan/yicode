package com.yixihan.yicode.thirdpart.openapi.biz.service.code.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.code.PhotoCodeFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.PhotoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private PhotoCodeFeignClient codeFeignClient;
    
    @Override
    public void createCode(HttpServletResponse response, String uuid) throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha (200, 100, 5, 20);
        String code = captcha.getCode ();
        
        codeFeignClient.createCode (code, uuid);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write (response.getOutputStream ());
    }
    
    @Override
    public CommonVO<Boolean> validateCode(CodeValidateReq req) {
        CodeValidateDtoReq dtoReq = CopyUtils.copySingle (CodeValidateDtoReq.class, req);
        CommonDtoResult<Boolean> dtoResult = codeFeignClient.validateCode (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
}
