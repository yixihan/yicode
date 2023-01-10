package com.yixihan.yicode.thirdpart.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.thirdpart.biz.service.TemplateEmailService;
import com.yixihan.yicode.thirdpart.dal.mapper.TemplateEmailMapper;
import com.yixihan.yicode.thirdpart.dal.pojo.TemplateEmail;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮件模板表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
@Service
public class TemplateEmailServiceImpl extends ServiceImpl<TemplateEmailMapper, TemplateEmail> implements TemplateEmailService {

    @Override
    public String getEmailContent(Long id) {
        return baseMapper.selectById (id).getTemplateContent ();
    }
}
