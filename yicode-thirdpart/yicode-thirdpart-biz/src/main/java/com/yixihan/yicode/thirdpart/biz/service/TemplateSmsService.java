package com.yixihan.yicode.thirdpart.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.thirdpart.dal.pojo.TemplateSms;

/**
 * <p>
 * 短信模板表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
public interface TemplateSmsService extends IService<TemplateSms> {

    /**
     * 根据 id 获取 模板id
     *
     * @param id 模板 id
     * @return 模板内容
     */
    String getSMSTemplateId (Long id);

}
