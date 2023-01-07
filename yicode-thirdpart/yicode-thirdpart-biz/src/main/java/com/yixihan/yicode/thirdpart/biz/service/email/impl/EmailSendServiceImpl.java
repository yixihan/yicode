package com.yixihan.yicode.thirdpart.biz.service.email.impl;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.api.constant.code.CodeConstant;
import com.yixihan.yicode.thirdpart.api.constant.email.EmailConstant;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.enums.email.EmailTemplateEnums;
import com.yixihan.yicode.thirdpart.biz.service.EmailTemplateService;
import com.yixihan.yicode.thirdpart.biz.service.code.CodeService;
import com.yixihan.yicode.thirdpart.biz.service.email.EmailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
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
    private EmailTemplateService emailTemplateService;

    @Resource
    private CodeConstant codeConstant;

    @Resource
    private EmailConstant emailConstant;


    @Override
    public CommonDtoResult<Boolean> sendEmail(EmailSendDtoReq dtoReq) {

        EmailTemplateEnums emailType = EmailTemplateEnums.valueOf (dtoReq.getType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        String emailContent = emailTemplateService.getEmailContent (emailType.getId ());

        try {
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
            log.error ("邮件发送失败 : {}", e.getMessage ());
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.EMAIL_SEND_ERR.getErrorMsg ());
        }

        return new CommonDtoResult<> (Boolean.TRUE, "邮件发送成功");
    }

    @Override
    public CommonDtoResult<Boolean> validate(EmailValidateDtoReq dtoReq) {
        // 生成 keyName
        EmailTemplateEnums emailType = EmailTemplateEnums.valueOf (dtoReq.getEmailType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        return codeService.validate (keyName, dtoReq.getCode ());
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
                key = String.format (emailConstant.getLoginKey (), email);
                break;
            case REGISTER:
                key = String.format (emailConstant.getRegisterKey (), email);
                break;
            case RESET_PASSWORD:
                key = String.format (emailConstant.getUpdatePasswordKey (), email);
                break;
            case COMMON:
                key = String.format (emailConstant.getCommonKey (), email);
                break;
            default:
                throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }

        return key;
    }

}
