package com.yixihan.yicode.thirdpart.openapi.biz.service.email.impl;

import com.yixihan.yicode.common.enums.EmailTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendEmailDtoReq;
import com.yixihan.yicode.thirdpart.openapi.biz.constant.CodeConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.constant.EmailConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.service.EmailTemplateService;
import com.yixihan.yicode.thirdpart.openapi.biz.service.impl.CodeServiceImpl;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送服务类
 *
 * @author yixihan
 * @date 2022-10-26-11:02
 */
@Slf4j
@Service
public class EmailSendServiceImpl implements EmailSendService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private CodeServiceImpl codeService;

    @Resource
    private EmailTemplateService emailTemplateService;

    @Resource
    private CodeConstant codeConstant;

    @Resource
    private EmailConstant emailConstant;


    @Override
    public String sendEmail(SendEmailDtoReq dtoReq) {

        EmailTypeEnums emailType = EmailTypeEnums.valueOf (dtoReq.getType ());

        // 生成 keyName
        String keyName = getRedisKey (dtoReq, emailType);

        // TODO 获取模板内容
        String emailContent = emailTemplateService.getEmailContent (emailType.getId ());

        try {
            // 生成验证码
            String code = codeService.getCode(keyName);

            // 创建一个复杂的文件
            MimeMessage mailMessage = mailSender.createMimeMessage();

            // 组装邮件
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf-8");

            helper.setSubject(emailConstant.getTitle ());
            helper.setText(String.format (emailContent, code, codeConstant.getTimeOut ()),true);

            // 收件人
            helper.setTo(dtoReq.getEmail ());
            // 发件人
            helper.setFrom(emailConstant.getSendEmail ());

            // 发送
            mailSender.send(mailMessage);

        } catch (MessagingException e) {
            log.error ("邮件发送失败 : {}", e.getMessage (), new BizException (BizCodeEnum.EMAIL_SEND_ERR));
        }

        return "邮件发送成功";
    }

    /**
     * 获取 redis key
     *
     * @param dtoReq
     * @param emailType
     * @return
     */
    private String getRedisKey (SendEmailDtoReq dtoReq, EmailTypeEnums emailType) {

        String key;
        if (emailType == EmailTypeEnums.LOGIN) {
            key = String.format (emailConstant.getLoginKey (), dtoReq.getEmail ());
        } else if (emailType == EmailTypeEnums.REGISTER) {
            key = String.format (emailConstant.getRegisterKey (), dtoReq.getEmail ());
        } else if (emailType == EmailTypeEnums.UPDATE_PASSWORD) {
            key = String.format (emailConstant.getUpdatePasswordKey (), dtoReq.getEmail ());
        } else if (emailType == EmailTypeEnums.COMMON) {
            key = String.format (emailConstant.getUpdatePasswordKey (), dtoReq.getEmail ());
        } else {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }

        return key;
    }

}
