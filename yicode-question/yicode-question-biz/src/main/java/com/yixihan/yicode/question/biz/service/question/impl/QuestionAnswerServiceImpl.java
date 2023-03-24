package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.enums.question.CodeAnswerEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CodeRateDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionNumberDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionAnswerService;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionAnswerMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionAnswer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 问题答案表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer> implements QuestionAnswerService {
    
    @Resource
    private QuestionService questionService;
    
    @Override
    public QuestionAnswerDtoResult addQuestionAnswer(AddQuestionAnswerDtoReq dtoReq) {
        QuestionAnswer questionAnswer = BeanUtil.toBean (dtoReq, QuestionAnswer.class);
    
        // 保存
        Assert.isTrue (save (questionAnswer), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        
        if (CodeAnswerEnums.AC.getAnswer ().equals (dtoReq.getAnswerFlag ())) {
            // 更新问题提交通过次数
            questionService.modifyQuestionSuccessCount (dtoReq.getQuestionId (),
                    questionService.getById (dtoReq.getQuestionId ()).getSuccessCount ());
        }
        // 更新问题提交次数
        questionService.modifyQuestionCommitCount (dtoReq.getQuestionId (),
                questionService.getById (dtoReq.getQuestionId ()).getCommitCount ());
        
        return BeanUtil.toBean (questionAnswer, QuestionAnswerDtoResult.class);
    }
    
    @Override
    public QuestionAnswerDtoResult questionAnswerDetail(Long questionAnswerId) {
        QuestionAnswer questionAnswer = getById (questionAnswerId);
        Assert.notNull (questionAnswer, new BizException ("没有该提交记录"));
    
        return BeanUtil.toBean (questionAnswer, QuestionAnswerDtoResult.class);
    }
    
    @Override
    public PageDtoResult<QuestionAnswerDtoResult> queryQuestionAnswer(QuestionAnswerDtoReq dtoReq) {
        Page<QuestionAnswer> page = lambdaQuery ()
                .eq (QuestionAnswer::getQuestionId, dtoReq.getQuestionId ())
                .eq (QuestionAnswer::getUserId, dtoReq.getUserId ())
                .orderByDesc (QuestionAnswer::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, QuestionAnswerDtoResult.class)
        );
    }
    
    @Override
    public PageDtoResult<QuestionAnswerDtoResult> queryUserQuestionAnswer(UserQuestionAnswerDtoReq dtoReq) {
        Page<QuestionAnswerDtoResult> pages = baseMapper.queryUserQuestionAnswer (dtoReq, PageUtil.toPage (dtoReq));
    
        return PageUtil.pageToPageDtoResult (pages);
    }
    
    @Override
    public List<CommitRecordDtoResult> codeCommitCount(CodeCommitCountDtoReq dtoReq) {
        // 日期-提交次数
        Map<DateTime, CommitRecordDtoResult> commitRecordDtoResults = baseMapper.codeCommitCount (dtoReq).stream ()
                .collect (Collectors.toMap (
                        o -> DateUtil.parse (o.getDate (), DatePattern.NORM_MONTH_PATTERN),
                        Function.identity (),
                        (k1, k2) -> k1
                ));
        
        // 获取起始时间和截至时间
        DateTime startDate = DateUtil.parse (dtoReq.getStartDate (), DatePattern.NORM_MONTH_PATTERN);
        DateTime endDate = DateUtil.parse (dtoReq.getEndDate (), DatePattern.NORM_MONTH_PATTERN);
        
        List<CommitRecordDtoResult> list = new ArrayList<> ();
        
        while (DateUtil.compare (startDate, endDate) < 0) {
            String date = DateUtil.format (startDate, DatePattern.NORM_MONTH_PATTERN);
            list.add (commitRecordDtoResults.getOrDefault (
                    startDate,
                    new CommitRecordDtoResult (date, 0))
            );
            
            // 偏移天
            startDate = DateUtil.offsetDay (startDate, 1);
        }
        
        // 排序
        list.sort ((o1, o2) -> compareDate(o1.getDate (), o2.getDate ()));
        return list;
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
                        o -> o.getQuestionId () + ":" + o.getQuestionDifficulty (),
                        HashMap::new,
                        Collectors.toList ()));
    
        // 计算通过&未通过题目数
        AtomicInteger acceptedQuestion = new AtomicInteger ();
        AtomicInteger acceptedHardQuestion = new AtomicInteger ();
        AtomicInteger acceptedMediumQuestion = new AtomicInteger ();
        AtomicInteger acceptedEasyQuestion = new AtomicInteger ();
        AtomicInteger unAcceptedQuestion = new AtomicInteger ();
        
        answerMap.forEach ((k, v) -> {
            if (v.stream ().anyMatch (o -> o.getAnswerFlag ().equals (CodeAnswerEnums.AC.getAnswer ()))) {
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
                .filter (o -> o.getAnswerFlag ().equals (CodeAnswerEnums.AC.getAnswer ()))
                .count ());
        
        // 计算百分比数值
        BigDecimal acceptedCommitRate = BigDecimal.ZERO;
        BigDecimal acceptedQuestionRate = BigDecimal.ZERO;
        BigDecimal acceptedHardQuestionRate = BigDecimal.ZERO;
        BigDecimal acceptedMediumQuestionRate = BigDecimal.ZERO;
        BigDecimal acceptedEasyQuestionRate = BigDecimal.ZERO;
        if (acceptedCommitCount != 0) {
            acceptedCommitRate = NumberUtil.mul (NumberUtil.div (
                    NumberUtil.toBigDecimal (acceptedCommitCount),
                    NumberUtil.toBigDecimal (commitCount),
                    4,
                    RoundingMode.HALF_UP), 100);
        }
        if (acceptedQuestion.get () != 0) {
            acceptedQuestionRate = NumberUtil.mul (NumberUtil.div (
                    NumberUtil.toBigDecimal (acceptedQuestion.get ()),
                    NumberUtil.toBigDecimal (questionNumber.getQuestionCount ()),
                    4,
                    RoundingMode.HALF_UP), 100);
        }
        if (acceptedHardQuestion.get () != 0) {
            acceptedHardQuestionRate = NumberUtil.mul (NumberUtil.div (
                    NumberUtil.toBigDecimal (acceptedHardQuestion.get ()),
                    NumberUtil.toBigDecimal (questionNumber.getHardQuestionCount ()),
                    4,
                    RoundingMode.HALF_UP), 100);
        }
        if (acceptedMediumQuestion.get () != 0) {
            acceptedMediumQuestionRate = NumberUtil.mul (NumberUtil.div (
                    NumberUtil.toBigDecimal (acceptedMediumQuestion.get ()),
                    NumberUtil.toBigDecimal (questionNumber.getMediumQuestionCount ()),
                    4,
                    RoundingMode.HALF_UP), 100);
        }
        if (acceptedEasyQuestion.get () != 0) {
            acceptedEasyQuestionRate = NumberUtil.mul (NumberUtil.div (
                    NumberUtil.toBigDecimal (acceptedEasyQuestion.get ()),
                    NumberUtil.toBigDecimal (questionNumber.getEasyQuestionCount ()),
                    4,
                    RoundingMode.HALF_UP), 100);
        }
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
                acceptedEasyQuestionRate
        );
    }
    
    private int compareDate (String date1, String date2) {
        return DateUtil.compare (
                DateUtil.parse (date1, DatePattern.NORM_MONTH_PATTERN),
                DateUtil.parse (date2, DatePattern.NORM_MONTH_PATTERN),
                DatePattern.NORM_MONTH_PATTERN
        );
    }
}
