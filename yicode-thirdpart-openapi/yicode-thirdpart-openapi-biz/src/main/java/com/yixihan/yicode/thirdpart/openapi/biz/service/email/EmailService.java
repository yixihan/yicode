package com.yixihan.yicode.thirdpart.openapi.biz.service.email;

import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailValidateReq;

/**
 * 邮件模块 服务类
 *
 * @author yixihan
 * @date 2022/11/23 14:53
 */
public interface EmailService {

    /**
     * 登录-发送邮件验证码
     *
     * @param req 请求参数
     */
    void loginSend(EmailSendReq req);

    /**
     * 登录-验证邮件验证码
     *
     * @param req 请求参数
     */
    void loginValidate(EmailValidateReq req);

    /**
     * 注册-发送邮件验证码
     *
     * @param req 请求参数
     */
    void registerSend(EmailSendReq req);

    /**
     * 注册-验证邮件验证码
     *
     * @param req 请求参数
     */
    void registerValidate(EmailValidateReq req);

    /**
     * 重置密码-发送邮件验证码
     *
     * @param req 请求参数
     */
    void resetSend(EmailSendReq req);

    /**
     * 重置密码-验证邮件验证码
     *
     * @param req 请求参数
     */
    void resetValidate(EmailValidateReq req);

    /**
     * 通用-发送邮件验证码
     *
     * @param req 请求参数
     */
    void commonSend(EmailSendReq req);

    /**
     * 通用-验证邮件验证码
     *
     * @param req 请求参数
     */
    void commonValidate(EmailValidateReq req);
}
