package com.yixihan.yicode.question.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
    public CommonDtoResult<Boolean> addQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        QuestionCase questionCase = BeanUtil.toBean (dtoReq, QuestionCase.class);
    
        int modify = baseMapper.insert (questionCase);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        QuestionCase questionCase = BeanUtil.toBean (dtoReq, QuestionCase.class);
        questionCase.setVersion (baseMapper.selectById (dtoReq.getId ()).getVersion ());
    
        int modify = baseMapper.updateById (questionCase);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delQuestionCase(Long id) {
        int modify = baseMapper.deleteById (id);
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<QuestionCaseDtoResult> allQuestionCase(Long questionId) {
        List<QuestionCase> questionCaseList = baseMapper.selectList (new QueryWrapper<QuestionCase> ()
                .eq (QuestionCase.QUESTION_ID, questionId)
                .eq (QuestionCase.ENABLE, NumConstant.NUM_1));
        
        questionCaseList = CollectionUtil.isEmpty (questionCaseList) ? Collections.emptyList () : questionCaseList;
        return BeanUtil.copyToList (questionCaseList, QuestionCaseDtoResult.class);
    }
    
    @Override
    public CommonDtoResult<Boolean> verifyQuestionCase(Long id) {
        return new CommonDtoResult<> (baseMapper.selectCount (new QueryWrapper<QuestionCase> ()
                .eq (QuestionCase.ID, id)) > 0);
    }
}
