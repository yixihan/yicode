package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionDailyMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDaily;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 每日一题表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionDailyServiceImpl extends ServiceImpl<QuestionDailyMapper, QuestionDaily> implements QuestionDailyService {
    
    @Override
    public CommonDtoResult<Boolean> addDailyQuestion(Long questionId) {
        return null;
    }
}
