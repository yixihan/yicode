package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCaseVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionCaseFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题测试用例管理 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:23
 */
@Slf4j
@Service
public class QuestionCaseServiceImpl implements QuestionCaseService {
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Resource
    private QuestionCaseFeignClient questionCaseFeignClient;
    
    @Override
    public QuestionCaseVO addQuestionCase(ModifyQuestionCaseReq req) {
        // 参数校验
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ());
        Assert.isFalse (StrUtil.isBlank (req.getCaseParams ()));
        Assert.isFalse (StrUtil.isBlank (req.getCaseAnswer ()));
        
        // 构建请求 body
        ModifyQuestionCaseDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionCaseDtoReq.class);
        // 默认不启用
        dtoReq.setEnable (Boolean.FALSE);
        
        // 添加测试用例
        QuestionCaseDtoResult dtoResult = questionCaseFeignClient.addQuestionCase (dtoReq).getResult ();
        
        return BeanUtil.toBean (dtoResult, QuestionCaseVO.class);
    }
    
    @Override
    public QuestionCaseVO modifyQuestionCase(ModifyQuestionCaseReq req) {
        // 参数校验
        Assert.isTrue (questionCaseFeignClient.verifyQuestionCase (req.getId ()).getResult ());
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ());
        Assert.isFalse (StrUtil.isBlank (req.getCaseParams ()));
        Assert.isFalse (StrUtil.isBlank (req.getCaseAnswer ()));
    
        // 构建请求 body
        ModifyQuestionCaseDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionCaseDtoReq.class);
    
        // 修改测试用例
        QuestionCaseDtoResult dtoResult = questionCaseFeignClient.modifyQuestionCase (dtoReq).getResult ();
        
        return BeanUtil.toBean (dtoResult, QuestionCaseVO.class);
    }
    
    @Override
    public void delQuestionCase(Long id) {
        // 参数校验
        Assert.isTrue (questionCaseFeignClient.verifyQuestionCase (id).getResult ());
        
        // 删除测试用例
        questionCaseFeignClient.delQuestionCase (id);
    }
    
    @Override
    public List<QuestionCaseVO> allQuestionCase(Long questionId) {
        // 参数校验
        Assert.isTrue (questionFeignClient.verifyQuestion (questionId).getResult ());
        
        // 获取测试用例
        List<QuestionCaseDtoResult> dtoResultList = questionCaseFeignClient.allQuestionCase (questionId).getResult ();
        
        return BeanUtil.copyToList (dtoResultList, QuestionCaseVO.class);
    }
}
