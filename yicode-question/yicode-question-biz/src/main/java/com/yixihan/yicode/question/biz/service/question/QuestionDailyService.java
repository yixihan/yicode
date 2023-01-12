package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDaily;

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
     * 生成每日一题
     *
     * @param questionId 问题 ID
     */
    CommonDtoResult<Boolean> addDailyQuestion(Long questionId);
}
