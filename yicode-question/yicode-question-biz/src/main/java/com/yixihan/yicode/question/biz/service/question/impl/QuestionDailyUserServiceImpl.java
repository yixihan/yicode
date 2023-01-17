package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.AddUserDailyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserDailyQuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserDailyQuestionDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyUserService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionDailyUserMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDailyUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户每日一题表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionDailyUserServiceImpl extends ServiceImpl<QuestionDailyUserMapper, QuestionDailyUser> implements QuestionDailyUserService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * redis key : 用户每日一题做题情况
     */
    private static final String USER_DAILY_QUESTION_KEY = "question_daily:user:%s";
    
    @Override
    public CommonDtoResult<Boolean> addUserDailyQuestion(AddUserDailyQuestionDtoReq dtoReq) {
        QuestionDailyUser questionDailyUser = BeanUtil.toBean (dtoReq, QuestionDailyUser.class);
    
        // 存入数据库
        baseMapper.insert (questionDailyUser);
        
        // 获取 redis key
        String key = String.format (USER_DAILY_QUESTION_KEY, dtoReq.getUserId ());
        
        // 获取连续做题天数
        int count = Boolean.TRUE.equals (redisTemplate.hasKey (key)) ?
                Integer.parseInt (Optional.ofNullable (redisTemplate.opsForValue ().get (key)).orElse ("0").toString ()) :
                NumConstant.NUM_0;
    
        // 获取和明天最后时刻的时间差
        Date nowTime = new Date ();
        DateTime tomorrow = DateUtil.endOfDay (DateUtil.offsetDay (nowTime, 1));
        long diff = Math.abs (tomorrow.between (nowTime, DateUnit.SECOND));
        
        // 存入 redis
        redisTemplate.opsForValue ().set (key, count + 1, diff, TimeUnit.SECONDS);
        
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Integer> dailyQuestionCount(Long userId) {
        String key = String.format (USER_DAILY_QUESTION_KEY, userId);
        int count = Boolean.TRUE.equals (redisTemplate.hasKey (key)) ?
                Integer.parseInt (Optional.ofNullable (redisTemplate.opsForValue ().get (key)).orElse ("0").toString ()) :
                NumConstant.NUM_0;
        return new CommonDtoResult<> (count);
    }
    
    @Override
    public List<UserDailyQuestionDtoResult> monthUserDailyQuestionDetail(UserDailyQuestionDetailDtoReq dtoReq) {
        DateTime startMonth = DateUtil.beginOfMonth (DateUtil.parse (dtoReq.getStartMonth (), "yyyy-MM"));
        DateTime endMonth = DateUtil.endOfMonth (DateUtil.parse (dtoReq.getStartMonth (), "yyyy-MM"));
    
        List<QuestionDailyUser> dtoResultList = baseMapper.selectList (new QueryWrapper<QuestionDailyUser> ()
                .eq (QuestionDailyUser.USER_ID, dtoReq.getUserId ())
                .between (QuestionDailyUser.CREATE_TIME, startMonth, endMonth));
        
        return BeanUtil.copyToList (dtoResultList, UserDailyQuestionDtoResult.class);
    }
}
