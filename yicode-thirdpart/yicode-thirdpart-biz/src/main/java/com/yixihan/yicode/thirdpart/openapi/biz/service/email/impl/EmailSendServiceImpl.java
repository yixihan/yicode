package com.yixihan.yicode.thirdpart.openapi.biz.service.email.impl;

import com.yixihan.yicode.common.enums.EmailTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.constant.CodeConstant;
import com.yixihan.yicode.thirdpart.openapi.api.constant.EmailConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.service.CodeService;
import com.yixihan.yicode.thirdpart.openapi.biz.service.EmailTemplateService;
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
    private CodeService codeService;

    @Resource
    private EmailTemplateService emailTemplateService;

    @Resource
    private CodeConstant codeConstant;

    @Resource
    private EmailConstant emailConstant;


    @Override
    public CommonDtoResult<Boolean> sendEmail(EmailSendDtoReq dtoReq) {

        EmailTypeEnums emailType = EmailTypeEnums.valueOf (dtoReq.getType ());
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
            log.error ("邮件发送失败 : {}", e.getMessage (), new BizException (BizCodeEnum.EMAIL_SEND_ERR));
            return new CommonDtoResult<> (false, "邮箱发送失败");
        }

        return new CommonDtoResult<> (true, "邮件发送成功");
    }

    @Override
    public CommonDtoResult<Boolean> validate(EmailValidateDtoReq dtoReq) {
        // 生成 keyName
        EmailTypeEnums emailType = EmailTypeEnums.valueOf (dtoReq.getEmailType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        return codeService.validate (keyName, dtoReq.getCode ());
    }


    /**
     * 获取 redis key
     *
     * @param email email
     * @param emailType 邮箱类型 {@link EmailTypeEnums}
     * @return
     */
    private String getRedisKey (String email, EmailTypeEnums emailType) {
        String key;
        switch (emailType) {
            case LOGIN:
                key = String.format (emailConstant.getLoginKey (), email);
                break;
            case REGISTER:
                key = String.format (emailConstant.getRegisterKey (), email);
                break;
            case UPDATE_PASSWORD:
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
