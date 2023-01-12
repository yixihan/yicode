package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
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
    
    @Override
    public CommonDtoResult<Boolean> addQuestionAnswer(AddQuestionAnswerDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public QuestionAnswerDtoResult questionAnswerDetail(Long questionAnswerId) {
        return null;
    }
    
    @Override
    public PageDtoResult<QuestionAnswerDtoResult> queryQuestionAnswer(QueryQuestionAnswerDtoReq dtoReq) {
        return null;
    }
}
