package com.yixihan.yicode.thirdpart.biz.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.thirdpart.biz.service.TemplateSmsService;
import com.yixihan.yicode.thirdpart.dal.mapper.TemplateSmsMapper;
import com.yixihan.yicode.thirdpart.dal.pojo.TemplateSms;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.SMS_TEMPLATE_KEY;

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
    
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 将数据库种的所有模板提取到 Redis 中<br>
     * 执行频率
     * <li>项目启动时执行一次</li>
     * <li>每一天执行一次</li>
     */
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void initMessageTemplate() {
        List<TemplateSms> templateList = list ();
        JSONArray array = JSONUtil.createArray ();
        array.addAll (templateList);
        redisTemplate.opsForValue ().set (SMS_TEMPLATE_KEY, array);
    }
    
    @Override
    public String getSMSTemplateId(String templateName) {
        String template;
        try {
            String jsonStr = JSONUtil.toJsonStr (redisTemplate.opsForValue ().get (SMS_TEMPLATE_KEY));
            template = JSONUtil.parseArray (jsonStr)
                    .toList (TemplateSms.class)
                    .stream ()
                    .filter (o -> o.getTemplateName ().equals (templateName))
                    .findFirst ()
                    .orElse (getTemplateByName (templateName))
                    .getTemplateId ();
        } catch (Exception e) {
            template = getTemplateByName (templateName).getTemplateId ();
        }
        return template;
    }
    
    /**
     * 通过模板名获取模板
     *
     * @param templateName 模板名
     * @return {@link TemplateSms}
     */
    private TemplateSms getTemplateByName (String templateName) {
        return lambdaQuery ()
                .eq (TemplateSms::getTemplateName, templateName)
                .one ();
    }
}
