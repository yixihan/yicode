package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionCaseService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionCaseMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionCase;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 问题测试用例表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionCaseServiceImpl extends ServiceImpl<QuestionCaseMapper, QuestionCase> implements QuestionCaseService {
    
    @Override
    public QuestionCaseDtoResult addQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        QuestionCase questionCase = BeanUtil.toBean (dtoReq, QuestionCase.class);
    
        // 保存
        Assert.isTrue (save (questionCase), BizCodeEnum.FAILED_TYPE_BUSINESS);
        return BeanUtil.toBean (questionCase, QuestionCaseDtoResult.class);
    }
    
    @Override
    public QuestionCaseDtoResult modifyQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        QuestionCase questionCase = BeanUtil.toBean (dtoReq, QuestionCase.class);
    
        // 获取乐观锁
        Integer version = lambdaQuery ()
                .select (QuestionCase::getVersion)
                .eq (QuestionCase::getId, dtoReq.getId ())
                .one ()
                .getVersion ();
        Assert.notNull (version, BizCodeEnum.ACCOUNT_NOT_FOUND);
        questionCase.setVersion (version);
    
        // 更新
        Assert.isTrue (updateById (questionCase), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return BeanUtil.toBean (getById (dtoReq.getId ()), QuestionCaseDtoResult.class);
    }
    
    @Override
    public void delQuestionCase(Long id) {
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public List<QuestionCaseDtoResult> allQuestionCase(Long questionId) {
        List<QuestionCase> questionCaseList = lambdaQuery ()
                .eq (QuestionCase::getQuestionId, questionId)
                .eq (QuestionCase::getEnable, NumConstant.NUM_1)
                .orderByDesc (QuestionCase::getCreateTime)
                .list ();
        questionCaseList = CollectionUtil.isEmpty (questionCaseList) ? Collections.emptyList () : questionCaseList;
        
        return BeanUtil.copyToList (questionCaseList, QuestionCaseDtoResult.class);
    }
    
    @Override
    public Boolean verifyQuestionCase(Long id) {
        return lambdaQuery ()
                .eq (QuestionCase::getId, id)
                .count () > 0;
    }
}
