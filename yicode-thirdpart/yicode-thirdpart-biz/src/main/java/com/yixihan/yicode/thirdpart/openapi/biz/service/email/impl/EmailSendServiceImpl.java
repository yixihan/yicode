package com.yixihan.yicode.thirdpart.openapi.biz.service.email.impl;

import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendEmailDtoReq;
import com.yixihan.yicode.thirdpart.openapi.biz.constant.EmailConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.service.CodeService;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author yixihan
 * @date 2022-10-26-11:02
 */
@Slf4j
@Service
public class EmailSendServiceImpl implements EmailSendService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private CodeService codeService;

    @Resource
    private EmailConstant emailConstant;


    @Override
    public String sendEmail(SendEmailDtoReq dtoReq) {
        // 生成 keyName
        String keyName = String.format(EmailConstant.LOGIN_FORMAT, dtoReq.getEmail ());

        try {
            // 生成验证码
            String code = codeService.getCode(keyName);

            // 创建一个复杂的文件
            MimeMessage mailMessage = mailSender.createMimeMessage();

            // 组装邮件
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf-8");

            helper.setSubject("你好");
            helper.setText("<h1 style='color:red'>验证码为" + code + "</h1>" +
                    "<p>有效期为 10 分钟,.请尽快填写, 打死也不要告诉别人哦</p>",true);

            // 收件人
            helper.setTo(dtoReq.getEmail ());
            // 发件人
            helper.setFrom(emailConstant.getSendEmail ());

            // 发送
            mailSender.send(mailMessage);

        } catch (MessagingException e) {
            return "短信发送失败";
        }

        return "短信发送成功";
    }

}
