package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionCaseService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionCaseMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionCase;
import org.springframework.stereotype.Service;

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
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyQuestionCase(ModifyQuestionCaseDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delQuestionCase(Long questionCaseId) {
        return null;
    }
    
    @Override
    public List<QuestionCaseDtoResult> allQuestionCase(Long questionId) {
        return null;
    }
}
