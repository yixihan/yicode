package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionMapper;
import com.yixihan.yicode.question.dal.pojo.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 问题表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    
    @Override
    public CommonDtoResult<Boolean> addQuestion(ModifyQuestionDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyQuestion(ModifyQuestionDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delQuestion(List<Long> questionIdList) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> likeQuestion(LikeDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public QuestionDetailDtoResult questionDetail(Long questionId) {
        return null;
    }
    
    @Override
    public PageDtoResult<QuestionDtoResult> queryQuestion(QueryQuestionDtoReq dtoReq) {
        return null;
    }
}
