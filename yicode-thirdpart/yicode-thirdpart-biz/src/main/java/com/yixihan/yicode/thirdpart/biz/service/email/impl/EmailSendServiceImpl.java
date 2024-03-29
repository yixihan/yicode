package com.yixihan.yicode.thirdpart.biz.service.email.impl;

import com.yixihan.yicode.common.enums.thirdpart.email.EmailTemplateEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.prop.code.CodeProp;
import com.yixihan.yicode.thirdpart.api.prop.email.EmailProp;
import com.yixihan.yicode.thirdpart.biz.service.TemplateEmailService;
import com.yixihan.yicode.thirdpart.biz.service.code.CodeService;
import com.yixihan.yicode.thirdpart.biz.service.email.EmailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务实现类
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
    private CodeService codeService;

    @Resource
    private TemplateEmailService templateEmailService;

    @Resource
    private CodeProp codeProp;

    @Resource
    private EmailProp emailProp;


    @Override
    public void send(EmailSendDtoReq dtoReq) {
        EmailTemplateEnums emailType = EmailTemplateEnums.valueOf (dtoReq.getType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        String emailContent = templateEmailService.getEmailContent (emailType.getName ());

        try {
            String code = codeService.getCode(keyName);
            // 创建一个复杂的文件
            MimeMessage mailMessage = mailSender.createMimeMessage();
            // 组装邮件
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf-8");
            helper.setSubject(emailProp.getTitle ());
            helper.setText(String.format (emailContent, code, codeProp.getTimeOut ()),true);
            // 收件人
            helper.setTo(dtoReq.getEmail ());
            // 发件人
            helper.setFrom(emailProp.getSendEmail ());
            // 发送
            mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error ("邮件模块发生异常 : {}", e.getMessage (), e);
            throw new BizException (BizCodeEnum.EMAIL_SEND_ERR);
        }
    }

    @Override
    public void validate(EmailValidateDtoReq dtoReq) {
        // 生成 keyName
        EmailTemplateEnums emailType = EmailTemplateEnums.valueOf (dtoReq.getEmailType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        
        // 校验验证码
        codeService.validate (keyName, dtoReq.getCode ());
    }


    /**
     * 获取 redis key
     *
     * @param email email
     * @param emailType 邮箱类型 {@link EmailTemplateEnums}
     * @return redis key
     */
    private String getRedisKey (String email, EmailTemplateEnums emailType) {
        String key;
        switch (emailType) {
            case LOGIN:
                key = String.format (emailProp.getLoginKey (), email);
                break;
            case REGISTER:
                key = String.format (emailProp.getRegisterKey (), email);
                break;
            case RESET_PASSWORD:
                key = String.format (emailProp.getUpdatePasswordKey (), email);
                break;
            case COMMON:
                key = String.format (emailProp.getCommonKey (), email);
                break;
            default:
                throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }

        return key;
    }

}
