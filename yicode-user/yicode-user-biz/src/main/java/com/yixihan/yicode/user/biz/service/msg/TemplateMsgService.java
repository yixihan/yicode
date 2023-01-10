package com.yixihan.yicode.user.biz.service.msg;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.dal.pojo.msg.TemplateMsg;

/**
 * <p>
 * 消息模板表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-10
 */
public interface TemplateMsgService extends IService<TemplateMsg> {


    /**
     * 获取消息模板
     *
     * @param templateId 消息模板 ID
     * @return 模板内容
     */
    String getMessageTemplate (String templateId);

}
