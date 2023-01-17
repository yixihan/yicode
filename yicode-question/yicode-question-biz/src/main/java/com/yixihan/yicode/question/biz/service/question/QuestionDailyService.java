package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDailyDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDaily;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 每日一题表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface QuestionDailyService extends IService<QuestionDaily> {
    
    /**
     * 获取指定月每日一题
     *
     * @param month 月份
     */
    List<QuestionDailyDtoResult> dailyQuestionDetail(Date month);
}
