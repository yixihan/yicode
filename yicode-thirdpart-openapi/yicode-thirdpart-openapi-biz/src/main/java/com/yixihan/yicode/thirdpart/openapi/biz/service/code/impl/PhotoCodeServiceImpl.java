package com.yixihan.yicode.thirdpart.openapi.biz.service.code.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.code.PhotoCodeFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.PhotoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public void create(HttpServletResponse response, String uuid) {
        try {
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha (200, 100, 5, 20);
            String code = captcha.getCode ();
        
            // 保存验证码
            codeFeignClient.create (code, uuid);
            //图形验证码写出，可以写出到文件，也可以写出到流
            captcha.write (response.getOutputStream ());
        } catch (Exception e) {
            log.error ("图片验证码模块发生异常 : {}", e.getMessage (), e);
            throw new BizException (BizCodeEnum.PHOTO_CODE_ERR);
        }
    }
    
    @Override
    public void validate(CodeValidateReq req) {
        CodeValidateDtoReq dtoReq = BeanUtil.toBean (req, CodeValidateDtoReq.class);
        
        // 校验验证码
        codeFeignClient.validate (dtoReq);
    }
}
