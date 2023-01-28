package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserQuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRateVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionAnswerVO;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionCommitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提交答案 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:22
 */
@Slf4j
@Service
public class QuestionCommitServiceImpl implements QuestionCommitService {
    
    @Override
    public void test(CodeReq req) {
    
    }
    
    @Override
    public void commit(CodeReq req) {
    
    }
    
    @Override
    public PageVO<QuestionAnswerVO> queryQuestionAnswer(QuestionAnswerReq req) {
        return null;
    }
    
    @Override
    public PageVO<QuestionAnswerVO> queryUserQuestionAnswer(UserQuestionAnswerReq req) {
        return null;
    }
    
    @Override
    public List<List<Integer>> codeCommitCount(String year) {
        return null;
    }
    
    @Override
    public CodeRateVO codeRate() {
        return null;
    }
}
