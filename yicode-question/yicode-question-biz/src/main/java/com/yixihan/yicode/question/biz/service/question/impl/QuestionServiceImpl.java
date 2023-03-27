package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionDetailDtoReq;
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
    public QuestionDetailDtoResult addQuestion(ModifyQuestionDtoReq dtoReq) {
        Question question = BeanUtil.toBean (dtoReq, Question.class);
        question.setEnable (Boolean.FALSE);
    
        // 保存
        Assert.isTrue (save (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return BeanUtil.toBean (question, QuestionDetailDtoResult.class);
    }
    
    @Override
    public QuestionDetailDtoResult modifyQuestion(ModifyQuestionDtoReq dtoReq) {
        Question question = BeanUtil.toBean (dtoReq, Question.class);
        
        // 获取乐观锁
        Integer version = lambdaQuery ()
                .select (Question::getVersion)
                .eq (Question::getQuestionId, dtoReq.getQuestionId ())
                .one ()
                .getVersion ();
        Assert.notNull (version, new BizException ("该问题不存在"));
        question.setVersion (version);
        
        // 更新
        Assert.isTrue (updateById (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        QuestionDetailDtoReq detailDtoReq = new QuestionDetailDtoReq ();
        detailDtoReq.setQuestionId (dtoReq.getQuestionId ());
        return questionDetail (detailDtoReq);
    }
    
    @Override
    public void delQuestion(List<Long> questionIdList) {
        Assert.isTrue (removeByIds (questionIdList), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void likeQuestion(LikeDtoReq dtoReq) {
        Question question = getById (dtoReq.getSourceId ());
        question.setLikeCount (dtoReq.getLikeCount ());
    
        // 更新
        Assert.isTrue (updateById (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public QuestionDetailDtoResult questionDetail(QuestionDetailDtoReq dtoReq) {
        QuestionDetailDtoResult question = Optional.ofNullable (baseMapper.questionDetail (dtoReq))
                .orElse (new QuestionDetailDtoResult ());
        
        return BeanUtil.toBean (question, QuestionDetailDtoResult.class);
    }
    
    @Override
    public PageDtoResult<QuestionDtoResult> queryQuestion(QueryQuestionDtoReq dtoReq) {
        // 搜索问题
        Page<QuestionDtoResult> dtoResultPage = baseMapper.queryQuestion (dtoReq, PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                dtoResultPage,
                o -> BeanUtil.toBean (o, QuestionDtoResult.class)
        );
    }
    
    @Override
    public Boolean verifyQuestion(Long questionId) {
        return lambdaQuery ()
                .eq (Question::getQuestionId, questionId)
                .eq (Question::getEnable, Boolean.TRUE)
                .count () > 0;
    }
    
    @Override
    public void modifyQuestionCommentCount(Long questionId, Integer commentCount) {
        Question question = getById (questionId);
        question.setCommentCount (commentCount);
    
        // 更新
        Assert.isTrue (updateById (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void modifyQuestionNoteCount(Long questionId, Integer noteCount) {
        Question question = getById (questionId);
        question.setNoteCount (noteCount);
        
        // 更新
        Assert.isTrue (updateById (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void modifyQuestionCommitCount(Long questionId, Integer commitCount) {
        Question question = getById (questionId);
        question.setCommitCount (commitCount);
        
        // 更新
        Assert.isTrue (updateById (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void modifyQuestionSuccessCount(Long questionId, Integer successCount) {
        Question question = getById (questionId);
        question.setSuccessCount (successCount);
        
        // 更新
        Assert.isTrue (updateById (question), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Map<Long, String> questionName(List<Long> questionIdList) {
        return listByIds (questionIdList)
                .stream ()
                .collect (Collectors.toMap (
                        Question::getQuestionId,
                        Question::getQuestionName,
                        (k1, k2) -> k1));
    }
    
    @Override
    public Map<String, BrokenDataDtoResult> brokenData(AdminDataDtoReq dtoReq) {
        // 提交数&通过数
        Map<String, BrokenDataDtoResult> dtoResult = baseMapper.brokenCodeData (dtoReq);
        Map<String, BrokenDataDtoResult> commentRootData = baseMapper.brokenCommentRootData (dtoReq);
        Map<String, BrokenDataDtoResult> commentReplyData = baseMapper.brokenCommentReplyData (dtoReq);
        Map<String, BrokenDataDtoResult> noteData = baseMapper.brokenNoteData (dtoReq);
        Map<String, BrokenDataDtoResult> userData = baseMapper.brokenUserData (dtoReq);
        
        // 填充有效数据
        dtoResult.forEach ((k, v) -> {
            // 评论数
            v.setCommentCount (commentRootData.getOrDefault (k, new BrokenDataDtoResult ()).getCommentCount () +
                    commentReplyData.getOrDefault (k, new BrokenDataDtoResult ()).getCommentCount ());
    
            // 题解数
            v.setNoteCount (noteData.getOrDefault (k, new BrokenDataDtoResult ()).getNoteCount ());
    
            // 用户数
            v.setUserCount (userData.getOrDefault (k, new BrokenDataDtoResult ()).getUserCount ());
        });
        
        // 填充默认数据
        DateTime startDate = DateUtil.parse (dtoReq.getStartDate (), DatePattern.NORM_MONTH_PATTERN);
        DateTime endDate = DateUtil.parse (dtoReq.getEndDate (), DatePattern.NORM_MONTH_PATTERN);
    
        while (DateUtil.compare (startDate, endDate) < 0) {
            String key = DateUtil.format (startDate, DatePattern.NORM_MONTH_PATTERN);
            if (!dtoResult.containsKey (key)) {
                BrokenDataDtoResult defaultData = new BrokenDataDtoResult ();
                defaultData.setMonth (key);
                dtoResult.put (key, defaultData);
            }
            startDate = DateUtil.offsetMonth (startDate, NumConstant.NUM_1);
        }
        
        return dtoResult;
    }
    
    @Override
    public CommitDataDtoResult commitData(AdminDataDtoReq dtoReq) {
        return baseMapper.commitData (dtoReq);
    }
    
    @Override
    public QuestionDetailDtoResult randomQuestion() {
        // 获取问题总数
        int count = lambdaQuery ()
                .eq (Question::getEnable, Boolean.TRUE)
                .count ();
        
        // 生成随机数
        int randomInt = RandomUtil.randomInt (count);
        
        Question question = lambdaQuery ()
                .eq (Question::getEnable, Boolean.TRUE)
                .last ("limit " + randomInt + ", 1")
                .one ();
        
        return BeanUtil.toBean (question, QuestionDetailDtoResult.class);
    }
    
    @Override
    public QuestionCountDtoResult questionCount() {
        return baseMapper.questionCount ();
    }
}
