package com.yixihan.yicode.thirdpart.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.thirdpart.dal.pojo.EmailTemplate;

/**
 * <p>
 * 邮件模板表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
public interface EmailTemplateService extends IService<EmailTemplate> {

    /**
     * 获取邮件正文模板
     *
     * @param id
     * @return
     */
    String getEmailContent (Long id);
}
