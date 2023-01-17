package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
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
    public CommonVO<Boolean> addQuestionCase(ModifyQuestionCaseReq req) {
        // 参数校验
        if (!questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (StrUtil.isBlank (req.getCaseParams ()) || StrUtil.isBlank (req.getCaseAnswer ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        ModifyQuestionCaseDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionCaseDtoReq.class);
        // 默认不启用
        dtoReq.setEnable (NumConstant.NUM_0);
        
        // 添加测试用例
        CommonDtoResult<Boolean> dtoResult = questionCaseFeignClient.addQuestionCase (dtoReq).getResult ();
    
        // 如果操作失败
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> modifyQuestionCase(ModifyQuestionCaseReq req) {
        // 参数校验
        if (!questionCaseFeignClient.verifyQuestionCase (req.getId ()).getResult ().getData () ||
                !questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (StrUtil.isBlank (req.getCaseParams ()) || StrUtil.isBlank (req.getCaseAnswer ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构建请求 body
        ModifyQuestionCaseDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionCaseDtoReq.class);
    
        // 修改测试用例
        CommonDtoResult<Boolean> dtoResult = questionCaseFeignClient.modifyQuestionCase (dtoReq).getResult ();
    
        // 如果操作失败
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delQuestionCase(Long id) {
        // 参数校验
        if (!questionCaseFeignClient.verifyQuestionCase (id).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 修改测试用例
        CommonDtoResult<Boolean> dtoResult = questionCaseFeignClient.delQuestionCase (id).getResult ();
    
        // 如果操作失败
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public List<QuestionCaseVO> allQuestionCase(Long questionId) {
        // 参数校验
        if (!questionFeignClient.verifyQuestion (questionId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 获取测试用例
        List<QuestionCaseDtoResult> dtoResultList = questionCaseFeignClient.allQuestionCase (questionId).getResult ();
        
        return BeanUtil.copyToList (dtoResultList, QuestionCaseVO.class);
    }
}
