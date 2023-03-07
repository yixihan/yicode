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
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.admin.BrokenDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.admin.CommitDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCountDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionMapper;
import com.yixihan.yicode.question.dal.pojo.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        question.setVersion (baseMapper.selectById (dtoReq.getQuestionId ()).getVersion ());
        
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
        QuestionDetailDtoResult question = Optional.ofNullable (baseMapper.questionDetail (questionId))
                .orElse (new QuestionDetailDtoResult ());
        
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
                o -> BeanUtil.toBean (o, QuestionDtoResult.class)
        );
    }
    
    @Override
    public CommonDtoResult<Boolean> verifyQuestion(Long questionId) {
        return new CommonDtoResult<> (baseMapper.selectCount (new QueryWrapper<Question> ()
                .eq (Question.QUESTION_ID, questionId)) > 0);
    }
    
    @Override
    public void modifyQuestionCommentCount(Long questionId, Integer commentCount) {
        Question question = baseMapper.selectById (questionId);
        question.setCommentCount (commentCount);
        baseMapper.updateById (question);
    }
    
    @Override
    public void modifyQuestionNoteCount(Long questionId, Integer noteCount) {
        Question question = baseMapper.selectById (questionId);
        question.setNoteCount (noteCount);
        baseMapper.updateById (question);
    }
    
    @Override
    public void modifyQuestionCommitCount(Long questionId, Integer commitCount) {
        Question question = baseMapper.selectById (questionId);
        question.setCommitCount (commitCount);
        baseMapper.updateById (question);
    }
    
    @Override
    public void modifyQuestionSuccessCount(Long questionId, Integer successCount) {
        Question question = baseMapper.selectById (questionId);
        question.setSuccessCount (successCount);
        baseMapper.updateById (question);
    }
    
    @Override
    public Map<Long, String> questionName(List<Long> questionIdList) {
        return baseMapper.selectBatchIds (questionIdList).stream ()
                .collect (Collectors.toMap (
                        Question::getQuestionId,
                        Question::getQuestionName,
                        (k1, k2) -> k1
                ));
    }
    
    @Override
    public Map<String, BrokenDataDtoResult> brokenData(AdminDataDtoReq dtoReq) {
        // 提交数&通过数
        Map<String, BrokenDataDtoResult> dtoResult = baseMapper.brokenCodeData (dtoReq);
        Map<String, BrokenDataDtoResult> commentRootData = baseMapper.brokenCommentRootData (dtoReq);
        Map<String, BrokenDataDtoResult> commentReplyData = baseMapper.brokenCommentReplyData (dtoReq);
        Map<String, BrokenDataDtoResult> noteData = baseMapper.brokenNoteData (dtoReq);
        Map<String, BrokenDataDtoResult> userData = baseMapper.brokenUserData (dtoReq);
        
    
        dtoResult.forEach ((k, v) -> {
            // 评论数
            v.setCommentCount (commentRootData.getOrDefault (k, new BrokenDataDtoResult ()).getCommentCount () +
                    commentReplyData.getOrDefault (k, new BrokenDataDtoResult ()).getCommentCount ());
    
            // 题解数
            v.setNoteCount (noteData.getOrDefault (k, new BrokenDataDtoResult ()).getNoteCount ());
    
            // 用户数
            v.setUserCount (userData.getOrDefault (k, new BrokenDataDtoResult ()).getUserCount ());
        });
        
        return dtoResult;
    }
    
    @Override
    public CommitDataDtoResult commitData(AdminDataDtoReq dtoReq) {
        return baseMapper.commitData (dtoReq);
    }
    
    @Override
    public QuestionCountDtoResult questionCount() {
        return baseMapper.questionCount ();
    }
}
