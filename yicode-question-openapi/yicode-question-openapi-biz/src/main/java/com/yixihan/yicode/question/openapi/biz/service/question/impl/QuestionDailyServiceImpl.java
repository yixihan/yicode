package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
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
    
    private static final String DATE_FORMAT = "yyyy-MM";
    
    @Override
    public List<QuestionDailyVO> dailyQuestionDetail(String month) {
        Date date =StrUtil.isBlank (month) ? new Date () :  DateUtil.parse (month, DATE_FORMAT);
    
        List<QuestionDailyDtoResult> dtoResultList = questionDailyFeignClient.dailyQuestionDetail (date).getResult ();
        
        return BeanUtil.copyToList (dtoResultList, QuestionDailyVO.class);
    }
    
    @Override
    public CommonVO<Integer> dailyQuestionCount() {
        // 获取用户 ID
        Long userId = userService.getUser ().getUserId ();
    
        // 获取数据
        CommonDtoResult<Integer> dtoResult = questionDailyUserFeignClient.dailyQuestionCount (userId).getResult ();
        
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public List<UserDailyQuestionVO> userDailyQuestionDetail(UserDailyQuestionDetailReq req) {
        // 规范参数
        String month = DateUtil.format (new Date (), DATE_FORMAT);
        if (StrUtil.isBlank (req.getStartMonth ())) {
            req.setStartMonth (month);
        }
        if (StrUtil.isBlank (req.getEndMonth ())) {
            req.setEndMonth (month);
        }
        
        // 构建请求 body
        UserDailyQuestionDetailDtoReq dtoReq = BeanUtil.toBean (req, UserDailyQuestionDetailDtoReq.class);
    
        // 获取数据
        List<UserDailyQuestionDtoResult> dtoResultList = questionDailyUserFeignClient.userDailyQuestionDetail (dtoReq).getResult ();
    
        return BeanUtil.copyToList (dtoResultList, UserDailyQuestionVO.class);
    }
}
