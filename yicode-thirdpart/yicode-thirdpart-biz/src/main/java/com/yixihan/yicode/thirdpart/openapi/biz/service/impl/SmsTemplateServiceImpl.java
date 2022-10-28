package com.yixihan.yicode.thirdpart.openapi.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.thirdpart.dal.mapper.SmsTemplateMapper;
import com.yixihan.yicode.thirdpart.dal.pojo.SmsTemplate;
import com.yixihan.yicode.thirdpart.openapi.biz.service.SmsTemplateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信模板表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

    @Override
    public String getSMSTemplateId(Long id) {
        return baseMapper.selectById (id).getTemplateId ();
    }
}
