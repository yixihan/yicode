package com.yixihan.yicode.user.biz.service.msg.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.msg.TemplateMsgService;
import com.yixihan.yicode.user.dal.mapper.msg.TemplateMsgMapper;
import com.yixihan.yicode.user.dal.pojo.msg.TemplateMsg;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 消息模板表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-10
 */
@Service
public class TemplateMsgServiceImpl extends ServiceImpl<TemplateMsgMapper, TemplateMsg> implements TemplateMsgService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final String MESSAGE_TEMPLATE_KEY = "message_template_key";
    
    /**
     * 将数据库种的所有模板提取到 Redis 中<br>
     * 执行频率
     * <li>项目启动时执行一次</li>
     * <li>每隔十分钟执行一次</li>
     */
    @PostConstruct
    @Scheduled(cron = "0 0,10,20,30,40,50 * * * ?")
    public void initMessageTemplate () {
        List<TemplateMsg> templateMsgList = baseMapper.selectList (null);
        redisTemplate.opsForValue ().set (MESSAGE_TEMPLATE_KEY, JSONUtil.createArray ().addAll (templateMsgList));
    }
    
    @Override
    public String getMessageTemplate(String templateId) {
        String template;
        try {
            String jsonStr = JSONUtil.toJsonStr (redisTemplate.opsForValue ().get (MESSAGE_TEMPLATE_KEY));
            template =  JSONUtil.parseArray (jsonStr).toList (TemplateMsg.class).stream ()
                    .filter ((o) -> o.getTemplateId ().equals (templateId))
                    .findFirst ().orElse (new TemplateMsg ()).getTemplateContent ();
        } catch (Exception e) {
            template = baseMapper.selectOne (new QueryWrapper<TemplateMsg> ()
                    .eq (TemplateMsg.TEMPLATE_ID, templateId)).getTemplateContent ();
        }
        return template;
    }
}
