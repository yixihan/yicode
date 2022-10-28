package com.yixihan.yicode.thirdpart.openapi.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.thirdpart.dal.pojo.SmsTemplate;

/**
 * <p>
 * 短信模板表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
public interface SmsTemplateService extends IService<SmsTemplate> {

    /**
     * 根据 id 获取 模板id
     * @param id
     * @return
     */
    String getSMSTemplateId (Long id);

}
