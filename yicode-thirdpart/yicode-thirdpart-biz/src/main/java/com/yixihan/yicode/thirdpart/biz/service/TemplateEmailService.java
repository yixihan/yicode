package com.yixihan.yicode.thirdpart.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.thirdpart.dal.pojo.TemplateEmail;

/**
 * <p>
 * 邮件模板表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
public interface TemplateEmailService extends IService<TemplateEmail> {

    /**
     * 获取邮件正文模板
     *
     * @param templateName 模板名
     * @return 模板内容
     */
    String getEmailContent (String templateName);
}
