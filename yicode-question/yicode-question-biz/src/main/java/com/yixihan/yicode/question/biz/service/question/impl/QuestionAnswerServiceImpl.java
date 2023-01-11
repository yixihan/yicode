package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.question.biz.service.question.QuestionAnswerService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionAnswerMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题答案表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements QuestionAnswerService {

}
