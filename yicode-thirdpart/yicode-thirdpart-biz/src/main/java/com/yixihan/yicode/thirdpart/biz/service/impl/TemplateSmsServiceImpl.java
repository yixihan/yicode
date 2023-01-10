package com.yixihan.yicode.thirdpart.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.thirdpart.biz.service.TemplateSmsService;
import com.yixihan.yicode.thirdpart.dal.mapper.TemplateSmsMapper;
import com.yixihan.yicode.thirdpart.dal.pojo.TemplateSms;
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
public class TemplateSmsServiceImpl extends ServiceImpl<TemplateSmsMapper, TemplateSms> implements TemplateSmsService {

    @Override
    public String getSMSTemplateId(Long id) {
        return baseMapper.selectById (id).getTemplateId ();
    }
}
