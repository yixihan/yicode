package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
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
import java.util.*;
import java.util.stream.Collectors;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.DAILY_QUESTION_KEY;

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
    
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void addDailyQuestion() {
        // 获取题目总数
        int count = questionService.count ();
    
        // 生成当月 redis key
        Date nowTime = new Date ();
        String yearMonth = DateUtil.format (nowTime, DatePattern.NORM_MONTH_PATTERN);
        
        // 获取当月每日一题生成情况
        String jsonStr = Optional.ofNullable (redisTemplate.opsForHash ().get (DAILY_QUESTION_KEY, yearMonth))
                .orElse ("")
                .toString ();
        List<QuestionDailyDtoResult> array = StrUtil.isBlank (jsonStr) ?
                new ArrayList<> () :
                JSONUtil.parseArray (jsonStr).toList (QuestionDailyDtoResult.class);
        
        // 如果当天的每日一题已被创建, 则直接返回
        if (array.stream ().map (QuestionDailyDtoResult::getCreateTime).anyMatch (o ->
                DateUtil.betweenDay (o, nowTime, Boolean.TRUE) == NumConstant.NUM_0)) {
            return;
        }
        
        // 获取当月每日一题的问题 ID
        Set<Long> questionIdSet = array
                .stream ()
                .map (QuestionDailyDtoResult::getQuestionId)
                .collect (Collectors.toSet ());
    
        // 循环生成一个本月没生成过的每日一题
        Long dailyQuestionId;
        do {
            // 生成随机数
            int random = RandomUtil.randomInt (count);
    
            dailyQuestionId = questionService.lambdaQuery ()
                    .select (Question::getQuestionId)
                    .last ("limit " + random + ", 1")
                    .one ()
                    .getQuestionId ();
        } while (questionIdSet.contains (dailyQuestionId));
        
        // 存入数据库
        QuestionDaily questionDaily = new QuestionDaily ();
        questionDaily.setQuestionId (dailyQuestionId);
        baseMapper.insert (questionDaily);
        log.info ("questionDaily : {}", questionDaily);
        
        // 构建 jsonArray
        array.add (BeanUtil.toBean (questionDaily, QuestionDailyDtoResult.class));
        JSONArray jsonArray = JSONUtil.createArray ();
        jsonArray.addAll (array);
    
        // 加入 redis
        redisTemplate.opsForHash ().put (DAILY_QUESTION_KEY, yearMonth, JSONUtil.toJsonStr (jsonArray));
    }
    
    @Override
    public List<QuestionDailyDtoResult> dailyQuestionDetail(Date month) {
        // 获取起始时间和终止时间
        DateTime startMonth = DateUtil.beginOfMonth (month);
        DateTime endMonth = DateUtil.endOfMonth (month);
        List<QuestionDaily> questionDailyList = lambdaQuery ()
                .between (QuestionDaily::getCreateTime, startMonth, endMonth)
                .orderByDesc (QuestionDaily::getCreateTime)
                .list ();
        
        return BeanUtil.copyToList (questionDailyList, QuestionDailyDtoResult.class);
    }
}
