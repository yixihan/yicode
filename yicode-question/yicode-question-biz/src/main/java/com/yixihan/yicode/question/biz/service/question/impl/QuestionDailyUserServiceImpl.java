package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.Assert;
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

import static com.yixihan.yicode.common.constant.RedisKeyConstant.USER_DAILY_QUESTION_KEY;

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
    
    
    
    @Override
    public UserDailyQuestionDtoResult addUserDailyQuestion(AddUserDailyQuestionDtoReq dtoReq) {
        QuestionDailyUser questionDailyUser = BeanUtil.toBean (dtoReq, QuestionDailyUser.class);
    
        // 存入数据库
        Assert.isTrue (save (questionDailyUser), BizCodeEnum.FAILED_TYPE_BUSINESS);

        
        // 获取 redis key
        String key = String.format (USER_DAILY_QUESTION_KEY, dtoReq.getUserId ());
        
        // 获取连续做题天数
        int count = dailyQuestionCount (dtoReq.getUserId ());
    
        // 获取和明天最后时刻的时间差
        Date nowTime = new Date ();
        DateTime tomorrow = DateUtil.endOfDay (DateUtil.offsetDay (nowTime, 1));
        long diff = Math.abs (tomorrow.between (nowTime, DateUnit.SECOND));
        
        // 存入 redis
        redisTemplate.opsForValue ().set (key, count + 1, diff, TimeUnit.SECONDS);
        
        return BeanUtil.toBean (questionDailyUser, UserDailyQuestionDtoResult.class);
    }
    
    @Override
    public Integer dailyQuestionCount(Long userId) {
        String key = String.format (USER_DAILY_QUESTION_KEY, userId);
        return Boolean.TRUE.equals (redisTemplate.hasKey (key)) ?
                Integer.parseInt (Optional.ofNullable (redisTemplate.opsForValue ().get (key)).orElse ("0").toString ()) :
                NumConstant.NUM_0;
    }
    
    @Override
    public List<UserDailyQuestionDtoResult> monthUserDailyQuestionDetail(UserDailyQuestionDetailDtoReq dtoReq) {
        DateTime startMonth = DateUtil.beginOfMonth (DateUtil.parse (dtoReq.getStartMonth (),
                DatePattern.NORM_MONTH_PATTERN));
        DateTime endMonth = DateUtil.endOfMonth (DateUtil.parse (dtoReq.getStartMonth (),
                DatePattern.NORM_MONTH_PATTERN));
    
        List<QuestionDailyUser> dtoResultList = lambdaQuery ()
                .eq (QuestionDailyUser::getUserId, dtoReq.getUserId ())
                .between (QuestionDailyUser::getCreateTime, startMonth, endMonth)
                .orderByDesc (QuestionDailyUser::getCreateTime)
                .list ();
        
        return BeanUtil.copyToList (dtoResultList, UserDailyQuestionDtoResult.class);
    }
}
