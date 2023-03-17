package com.yixihan.yicode.thirdpart.openapi.biz.service.sms;

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
    void loginSend(SMSSendReq req);

    /**
     * 登录-验证邮件验证码
     *
     * @param req 请求参数
     */
    void loginValidate(SMSValidateReq req);

    /**
     * 注册-发送邮件验证码
     *
     * @param req 请求参数
     */
    void registerSend(SMSSendReq req);

    /**
     * 注册-验证邮件验证码
     *
     * @param req 请求参数
     */
    void registerValidate(SMSValidateReq req);

    /**
     * 重置密码-发送邮件验证码
     *
     * @param req 请求参数
     */
    void resetSend(SMSSendReq req);

    /**
     * 重置密码-验证邮件验证码
     *
     * @param req 请求参数
     */
    void resetValidate(SMSValidateReq req);

    /**
     * 通用-发送邮件验证码
     *
     * @param req 请求参数
     */
    void commonSend(SMSSendReq req);

    /**
     * 通用-验证邮件验证码
     *
     * @param req 请求参数
     */
    void commonValidate(SMSValidateReq req);
}
