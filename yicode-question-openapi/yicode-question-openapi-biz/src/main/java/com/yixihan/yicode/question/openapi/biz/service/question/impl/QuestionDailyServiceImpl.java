package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.question.api.dto.request.question.UserDailyQuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDailyDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.UserDailyQuestionDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserDailyQuestionDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDailyVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.UserDailyQuestionVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionDailyFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionDailyUserFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionDailyService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 每日一题 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:23
 */
@Slf4j
@Service
public class QuestionDailyServiceImpl implements QuestionDailyService {
    
    @Resource
    private QuestionDailyFeignClient questionDailyFeignClient;
    
    @Resource
    private QuestionDailyUserFeignClient questionDailyUserFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public Map<String, QuestionDailyVO> dailyQuestionDetail(String month) {
        Date date =StrUtil.isBlank (month) ?
                new Date () :
                DateUtil.parse (month, DatePattern.NORM_MONTH_PATTERN);
    
        List<QuestionDailyDtoResult> dtoResultList = questionDailyFeignClient.dailyQuestionDetail (date).getResult ();
        
        return BeanUtil.copyToList (dtoResultList, QuestionDailyVO.class).stream ()
                .collect (Collectors.toMap (
                        o -> DateUtil.format (o.getCreateTime (), DatePattern.NORM_DATE_PATTERN),
                        Function.identity (),
                        (k1, k2) -> k1)
                );
    }
    
    @Override
    public Integer dailyQuestionCount() {
        // 获取用户 ID
        Long userId = userService.getUserId ();
    
        // 获取数据
        return questionDailyUserFeignClient.dailyQuestionCount (userId).getResult ();
    }
    
    @Override
    public List<UserDailyQuestionVO> userDailyQuestionDetail(UserDailyQuestionDetailReq req) {
        // 规范参数
        String month = DateUtil.format (new Date (), DatePattern.NORM_MONTH_PATTERN);
        if (StrUtil.isBlank (req.getStartMonth ())) {
            req.setStartMonth (month);
        }
        if (StrUtil.isBlank (req.getEndMonth ())) {
            req.setEndMonth (month);
        }
        
        // 构建请求 body
        UserDailyQuestionDetailDtoReq dtoReq = BeanUtil.toBean (req, UserDailyQuestionDetailDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 获取数据
        List<UserDailyQuestionDtoResult> dtoResultList = questionDailyUserFeignClient.userDailyQuestionDetail (dtoReq).getResult ();
    
        return BeanUtil.copyToList (dtoResultList, UserDailyQuestionVO.class);
    }
}
