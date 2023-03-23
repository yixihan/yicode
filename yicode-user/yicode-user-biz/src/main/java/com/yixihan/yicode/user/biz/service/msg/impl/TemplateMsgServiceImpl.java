package com.yixihan.yicode.user.biz.service.msg.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
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

import static com.yixihan.yicode.common.constant.RedisKeyConstant.MESSAGE_TEMPLATE_KEY;

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
    
    /**
     * 将数据库种的所有模板提取到 Redis 中<br>
     * 执行频率
     * <li>项目启动时执行一次</li>
     * <li>每一天执行一次</li>
     */
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void initMessageTemplate() {
        List<TemplateMsg> templateList = baseMapper.selectList (null);
        JSONArray array = JSONUtil.createArray ();
        array.addAll (templateList);
        redisTemplate.opsForValue ().set (MESSAGE_TEMPLATE_KEY, array);
    }
    
    @Override
    public String getMessageTemplate(String templateId) {
        String template;
        try {
            String jsonStr = JSONUtil.toJsonStr (redisTemplate.opsForValue ().get (MESSAGE_TEMPLATE_KEY));
            template = JSONUtil.parseArray (jsonStr)
                    .toList (TemplateMsg.class)
                    .stream ()
                    .filter (o -> o.getTemplateId ().equals (templateId))
                    .findFirst ()
                    .orElse (getTemplateById(templateId))
                    .getTemplateContent ();
        } catch (Exception e) {
            template = getTemplateById(templateId).getTemplateContent ();
        }
        return template;
    }
    
    /**
     * 通过模板 id 获取模板
     *
     * @param templateId 模板 id
     * @return {@link TemplateMsg}
     */
    private TemplateMsg getTemplateById (String templateId) {
        return lambdaQuery ()
                .eq (TemplateMsg::getTemplateId, templateId)
                .one ();
    }
}
