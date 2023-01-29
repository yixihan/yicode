package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
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
import com.yixihan.yicode.question.api.dto.response.question.QuestionNumberDtoResult;
import com.yixihan.yicode.question.api.enums.CodeAnswerEnums;
import com.yixihan.yicode.question.biz.service.question.QuestionAnswerService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionAnswerMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
        
        // TODO 优化 返回所有的日期, 无论有无提交记录
        
        return commitRecordDtoResults.stream ()
                .collect (Collectors.groupingBy (
                        (item) -> item.getDate ().substring (0, item.getDate ().lastIndexOf ("-")),
                        HashMap::new,
                        Collectors.toList ()));
    }
    
    @Override
    public CodeRateDtoResult codeRate(Long userId) {
        // 获取用户提交代码情况
        List<QuestionAnswerDtoResult> questionAnswerList = baseMapper.codeRate (userId);
        
        // 获取题目数量情况
        QuestionNumberDtoResult questionNumber = baseMapper.questionNumber ();
    
        // 按 questionId group by
        HashMap<String, List<QuestionAnswerDtoResult>> answerMap = questionAnswerList
                .stream ().collect (Collectors.groupingBy (
                        (o) -> o.getQuestionId () + ":" + o.getQuestionDifficulty (),
                        HashMap::new,
                        Collectors.toList ()));
    
        // 计算通过&未通过题目数
        AtomicInteger acceptedQuestion = new AtomicInteger ();
        AtomicInteger acceptedHardQuestion = new AtomicInteger ();
        AtomicInteger acceptedMediumQuestion = new AtomicInteger ();
        AtomicInteger acceptedEasyQuestion = new AtomicInteger ();
        AtomicInteger unAcceptedQuestion = new AtomicInteger ();
        
        answerMap.forEach ((k, v) -> {
            if (v.stream ().anyMatch ((o) -> o.getAnswerFlag ().equals (CodeAnswerEnums.AC.getAnswer ()))) {
                acceptedQuestion.getAndIncrement ();
                switch (k.split (":")[1]) {
                    case "EASY": {
                        acceptedEasyQuestion.getAndIncrement ();
                        break;
                    }
                    case "MEDIUM": {
                        acceptedMediumQuestion.getAndIncrement ();
                        break;
                    }
                    case "HARD": {
                        acceptedHardQuestion.getAndIncrement ();
                        break;
                    }
                    default: break;
                }
            } else {
                unAcceptedQuestion.getAndIncrement ();
            }
        });
        
        // 计算未作题目数
        int unDoQuestion = questionNumber.getQuestionCount () - acceptedQuestion.get () - unAcceptedQuestion.get ();
        int unDoHardQuestion = questionNumber.getHardQuestionCount () - acceptedHardQuestion.get ();
        int unDoMediumQuestion = questionNumber.getMediumQuestionCount () - acceptedMediumQuestion.get ();
        int unDoEasyQuestion = questionNumber.getEasyQuestionCount () - acceptedEasyQuestion.get ();
        
        // 计算总提交数&通过提交数
        int commitCount = questionAnswerList.size ();
        int acceptedCommitCount = Math.toIntExact (questionAnswerList.stream ()
                .filter ((o) -> o.getAnswerFlag ().equals (CodeAnswerEnums.AC.getAnswer ()))
                .count ());
        
        // 计算百分比数值
        BigDecimal acceptedCommitRate = NumberUtil.mul (NumberUtil.div (
                NumberUtil.toBigDecimal (acceptedCommitCount),
                NumberUtil.toBigDecimal (commitCount),
                4,
                RoundingMode.HALF_UP), 100);
        BigDecimal acceptedQuestionRate = NumberUtil.mul (NumberUtil.div (
                NumberUtil.toBigDecimal (acceptedQuestion.get ()),
                NumberUtil.toBigDecimal (questionNumber.getQuestionCount ()),
                4,
                RoundingMode.HALF_UP), 100);
        BigDecimal acceptedHardQuestionRate = NumberUtil.mul (NumberUtil.div (
                NumberUtil.toBigDecimal (acceptedHardQuestion.get ()),
                NumberUtil.toBigDecimal (questionNumber.getHardQuestionCount ()),
                4,
                RoundingMode.HALF_UP), 100);
        
        BigDecimal acceptedMediumQuestionRate = NumberUtil.mul (NumberUtil.div (
                NumberUtil.toBigDecimal (acceptedMediumQuestion.get ()),
                NumberUtil.toBigDecimal (questionNumber.getMediumQuestionCount ()),
                4,
                RoundingMode.HALF_UP), 100);
        BigDecimal acceptedEasyQuestionRate = NumberUtil.mul (NumberUtil.div (
                NumberUtil.toBigDecimal (acceptedEasyQuestion.get ()),
                NumberUtil.toBigDecimal (questionNumber.getEasyQuestionCount ()),
                4,
                RoundingMode.HALF_UP), 100);
        
        return new CodeRateDtoResult (
                acceptedQuestion.get (),
                acceptedHardQuestion.get (),
                acceptedMediumQuestion.get (),
                acceptedEasyQuestion.get (),
                unAcceptedQuestion.get (),
                unDoQuestion,
                unDoHardQuestion,
                unDoMediumQuestion,
                unDoEasyQuestion,
                questionNumber,
                commitCount,
                acceptedCommitCount,
                acceptedCommitRate,
                acceptedQuestionRate,
                acceptedHardQuestionRate,
                acceptedMediumQuestionRate,
                acceptedEasyQuestionRate);
    }
}
