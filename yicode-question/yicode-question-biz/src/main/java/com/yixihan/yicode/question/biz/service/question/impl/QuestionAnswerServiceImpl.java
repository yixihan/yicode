package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CodeRateDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionAnswerService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionAnswerMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        QuestionAnswer questionAnswer = BeanUtil.toBean (dtoReq, QuestionAnswer.class);
    
        int modify = baseMapper.insert (questionAnswer);
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public QuestionAnswerDtoResult questionAnswerDetail(Long questionAnswerId) {
        QuestionAnswer questionAnswer = baseMapper.selectById (questionAnswerId);
        
        questionAnswer = Optional.ofNullable (questionAnswer).orElse (new QuestionAnswer ());
        
        return BeanUtil.toBean (questionAnswer, QuestionAnswerDtoResult.class);
    }
    
    @Override
    public PageDtoResult<QuestionAnswerDtoResult> queryQuestionAnswer(QuestionAnswerDtoReq dtoReq) {
        Page<QuestionAnswer> pages = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ()),
                new QueryWrapper<QuestionAnswer> ()
                        .eq (QuestionAnswer.QUESTION_ID, dtoReq.getQuestionId ())
                        .eq (QuestionAnswer.USER_ID, dtoReq.getUserId ())
        );
        
        if (CollectionUtil.isEmpty (pages.getRecords ())) {
            return new PageDtoResult<> ();
        }
        
        return PageUtil.pageToPageDtoResult (
                pages,
                (o) -> BeanUtil.toBean (o, QuestionAnswerDtoResult.class)
        );
    }
    
    @Override
    public PageDtoResult<QuestionAnswerDtoResult> queryUserQuestionAnswer(UserQuestionAnswerDtoReq dtoReq) {
        Page<QuestionAnswerDtoResult> pages = baseMapper.queryUserQuestionAnswer (
                dtoReq,
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ())
        );
    
        if (CollectionUtil.isEmpty (pages.getRecords ())) {
            return new PageDtoResult<> ();
        }
    
        return PageUtil.pageToPageDtoResult (pages);
    }
    
    @Override
    public Map<String, List<CommitRecordDtoResult>> codeCommitCount(CodeCommitCountDtoReq dtoReq) {
        List<CommitRecordDtoResult> commitRecordDtoResults = baseMapper.codeCommitCount (dtoReq);
        
        return commitRecordDtoResults.stream ()
                .collect (Collectors.groupingBy (
                        (item) -> item.getDate ().substring (0, item.getDate ().lastIndexOf ("-")),
                        HashMap::new,
                        Collectors.toList ()));
    }
    
    @Override
    public CodeRateDtoResult codeRate(Long userId) {
        // TODO 待完善用户做题进度 sql 未完成
        return baseMapper.codeRate (userId);
    }
}
