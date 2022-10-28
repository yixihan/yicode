package com.yixihan.yicode.thirdpart.openapi.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.thirdpart.dal.mapper.EmailTemplateMapper;
import com.yixihan.yicode.thirdpart.dal.pojo.EmailTemplate;
import com.yixihan.yicode.thirdpart.openapi.biz.service.EmailTemplateService;
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
public class EmailTemplateServiceImpl extends ServiceImpl<EmailTemplateMapper, EmailTemplate> implements EmailTemplateService {

}
