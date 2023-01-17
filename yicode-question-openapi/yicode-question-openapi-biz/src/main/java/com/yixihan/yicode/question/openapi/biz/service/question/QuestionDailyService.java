package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserDailyQuestionDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDailyVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.UserDailyQuestionVO;

import java.util.List;

/**
 * 每日一题 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface QuestionDailyService {
    
    /**
     * 获取当月的每日一题详情
     *
     * @param month 月份
     */
    List<QuestionDailyVO> dailyQuestionDetail(String month);
    
    /**
     * 获取用户连续做每日一题的天数
     */
    CommonVO<Integer> dailyQuestionCount();
    
    /**
     * 查询用户每月每日一题情况
     *
     * @param req 请求参数
     */
    List<UserDailyQuestionVO> userDailyQuestionDetail(UserDailyQuestionDetailReq req);
}
