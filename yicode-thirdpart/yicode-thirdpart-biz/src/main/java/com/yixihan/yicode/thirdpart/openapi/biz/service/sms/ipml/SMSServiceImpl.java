package com.yixihan.yicode.thirdpart.openapi.biz.service.sms.ipml;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import com.yixihan.yicode.common.enums.SMSTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.openapi.biz.constant.CodeConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.constant.SMSConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.service.SmsTemplateService;
import com.yixihan.yicode.thirdpart.openapi.biz.service.impl.CodeServiceImpl;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信发送服务类
 *
 * @author yixihan
 * @date 2022-10-27-15:49
 */
@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

    @Resource
    private SMSConstant smsConstant;

    @Resource
    private CodeConstant codeConstant;

    @Resource
    private CodeServiceImpl codeService;

    @Resource
    private SmsTemplateService smsTemplateService;


    @Override
    public String send(SMSSendDtoReq dtoReq) {

        SMSTypeEnums smsType = SMSTypeEnums.valueOf (dtoReq.getType ());

        // 生成 keyName
        String keyName = getRedisKey (dtoReq.getMobile (), smsType);

        // 生成验证码
        String code = codeService.getCode(keyName);

        // TODO 获取模板 id
        String templateId = smsTemplateService.getSMSTemplateId (smsType.getId ());

        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(smsConstant.getSecretId (), smsConstant.getSecretKey ());
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {dtoReq.getMobile ()};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId(smsConstant.getSmsSdkAppId ());
            req.setSignName(smsConstant.getSignName ());
            req.setTemplateId(templateId);

            String[] templateParamSet1 = {code, String.valueOf (codeConstant.getTimeOut ())};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            log.info ("response : {}", SendSmsResponse.toJsonString(resp));
            SendStatus sendStatus = resp.getSendStatusSet ()[0];
            if ("Ok".equals (sendStatus.getCode ())) {
                return "短信发送成功";
            } else {
                log.error (sendStatus.getMessage(), new BizException (BizCodeEnum.SMS_SEND_ERR));
                return "短信发送失败";
            }
            // 输出json格式的字符串回包

        } catch (TencentCloudSDKException e) {
            log.error (e.getMessage (), new BizException (BizCodeEnum.SMS_SEND_ERR));
            return "短信发送失败";
        }
    }

    @Override
    public Boolean validate(SMSValidateDtoReq dtoReq) {
        // 生成 keyName
        SMSTypeEnums smsType = SMSTypeEnums.valueOf (dtoReq.getMobileType ());
        String keyName = getRedisKey (dtoReq.getMobile (), smsType);

        return codeService.validate (keyName, dtoReq.getCode ());
    }

    /**
     * 获取 redis key
     *
     * @param mobile
     * @param smsType
     * @return
     */
    private String getRedisKey (String mobile, SMSTypeEnums smsType) {
        String key;
        switch (smsType) {
            case LOGIN:
                key = String.format (smsConstant.getLoginKey (), mobile);
                break;
            case REGISTER:
                key = String.format (smsConstant.getRegisterKey (), mobile);
                break;
            case UPDATE_PASSWORD:
                key = String.format (smsConstant.getUpdatePasswordKey (), mobile);
                break;
            case COMMON:
                key = String.format (smsConstant.getCommonKey (), mobile);
                break;
            default:
                throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }

        return key;
    }
}
