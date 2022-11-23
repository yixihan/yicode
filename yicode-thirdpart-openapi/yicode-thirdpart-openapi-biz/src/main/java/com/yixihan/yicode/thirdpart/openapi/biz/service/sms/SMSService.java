package com.yixihan.yicode.thirdpart.openapi.biz.service.sms;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;

/**
 * 短信模块 服务类
 *
 * @author yixihan
 * @date 2022/11/23 14:53
 */
public interface SMSService {

    /**
     * 登录-发送邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> loginSend(SMSSendReq req);

    /**
     * 登录-验证邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> loginValidate(SMSValidateReq req);

    /**
     * 注册-发送邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> registerSend(SMSSendReq req);

    /**
     * 注册-验证邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> registerValidate(SMSValidateReq req);

    /**
     * 重置密码-发送邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> resetSend(SMSSendReq req);

    /**
     * 重置密码-验证邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> resetValidate(SMSValidateReq req);

    /**
     * 通用-发送邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> commonSend(SMSSendReq req);

    /**
     * 通用-验证邮件验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> commonValidate(SMSValidateReq req);
}
