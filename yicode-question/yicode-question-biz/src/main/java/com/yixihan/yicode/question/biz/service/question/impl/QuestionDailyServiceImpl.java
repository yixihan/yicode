package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDailyDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyService;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionDailyMapper;
import com.yixihan.yicode.question.dal.pojo.question.Question;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDaily;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 每日一题表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@Service
public class QuestionDailyServiceImpl extends ServiceImpl<QuestionDailyMapper, QuestionDaily> implements QuestionDailyService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    @Resource
    private QuestionService questionService;
    
    /**
     * redis key : 每日一题
     */
    private static final String DAILY_QUESTION_KEY = "question_daily:question";
    
    @PostConstruct
    @Scheduled(cron = "0 5 0 * * ?")
    public void addDailyQuestion() {
        // 获取题目总数
        int count = questionService.count ();
    
        // 生成当月 redis key
        Date nowTime = new Date ();
        String yearMonth = DateUtil.format (nowTime, "yyyy-MM");
        
        // 获取当月每日一题生成情况
        String jsonStr = Optional.ofNullable (redisTemplate.opsForHash ().get (DAILY_QUESTION_KEY, yearMonth))
                .orElse ("").toString ();
        List<QuestionDaily> array = JSONUtil.parseArray (jsonStr).toList (QuestionDaily.class);
        
        // 如果当天的每日一题已被创建, 则直接返回
        if (array.stream ().map (QuestionDaily::getCreateTime).anyMatch ((o) ->
                DateUtil.betweenDay (o, nowTime, Boolean.TRUE) == NumConstant.NUM_0)) {
            return;
        }
        
        // 获取当月每日一题的问题 ID
        Set<Long> questionIdSet = array.stream ()
                .map (QuestionDaily::getQuestionId)
                .collect (Collectors.toSet ());
    
        // 循环生成一个本月没生成过的每日一题
        Long dailyQuestionId;
        do {
            // 生成随机数
            int random = RandomUtil.randomInt (count + 1);
    
            dailyQuestionId = questionService.query ()
                    .select (Question.QUESTION_ID)
                    .last ("limit " + random + ", 1")
                    .one ().getQuestionId ();
        } while (questionIdSet.contains (dailyQuestionId));
        
        // 存入数据库
        QuestionDaily questionDaily = new QuestionDaily ();
        questionDaily.setQuestionId (dailyQuestionId);
        baseMapper.insert (questionDaily);
        log.info ("questionDaily : {}", questionDaily);
        
        // 构建 jsonArray
        array.add (questionDaily);
        JSONArray jsonArray = JSONUtil.createArray ();
        jsonArray.addAll (array);
    
        // 加入 redis
        redisTemplate.opsForHash ().put (DAILY_QUESTION_KEY, yearMonth, JSONUtil.toJsonStr (jsonArray));
    }
    
    @Override
    public List<QuestionDailyDtoResult> dailyQuestionDetail(Date month) {
        // 获取起始时间和终止时间
        DateTime startMonth = DateUtil.beginOfMonth (month);
        DateTime endMonth = DateUtil.endOfMinute (month);
        
        List<QuestionDaily> questionDailyList = baseMapper.selectList (new QueryWrapper<QuestionDaily> ()
                .between (QuestionDaily.CREATE_TIME, startMonth, endMonth));
        
        return BeanUtil.copyToList (questionDailyList, QuestionDailyDtoResult.class);
    }
}
