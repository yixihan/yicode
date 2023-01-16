package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.PageUtil;
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
import java.util.Optional;

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
        Question question = BeanUtil.toBean (dtoReq, Question.class);
    
        int modify = baseMapper.insert (question);
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyQuestion(ModifyQuestionDtoReq dtoReq) {
        Question question = BeanUtil.toBean (dtoReq, Question.class);
    
        int modify = baseMapper.updateById (question);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delQuestion(List<Long> questionIdList) {
        int modify = baseMapper.deleteBatchIds (questionIdList);
        
        if (modify < 0) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> likeQuestion(LikeDtoReq dtoReq) {
        Question question = baseMapper.selectById (dtoReq.getSourceId ());
        question.setLikeCount (dtoReq.getLikeCount ());
    
        int modify = baseMapper.updateById (question);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public QuestionDetailDtoResult questionDetail(Long questionId) {
        Question question = Optional.ofNullable (baseMapper.selectById (questionId))
                .orElse (new Question ());
        
        return BeanUtil.toBean (question, QuestionDetailDtoResult.class);
    }
    
    @Override
    public PageDtoResult<QuestionDtoResult> queryQuestion(QueryQuestionDtoReq dtoReq) {
        // 搜索问题
        Page<QuestionDtoResult> dtoResultPage = baseMapper.queryQuestion (
                dtoReq,
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ())
        );
        
        return PageUtil.pageToPageDtoResult (
                dtoResultPage,
                (o) -> BeanUtil.toBean (o, QuestionDtoResult.class)
        );
    }
    
    @Override
    public CommonDtoResult<Boolean> verifyQuestion(Long questionId) {
        return new CommonDtoResult<> (baseMapper.selectCount (new QueryWrapper<Question> ()
                .eq (Question.QUESTION_ID, questionId)) > 0);
    }
}
