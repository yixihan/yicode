package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionCase;

import java.util.List;

/**
 * <p>
 * 问题测试用例表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface QuestionCaseService extends IService<QuestionCase> {
    
    /**
     * 添加测试用例
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addQuestionCase(ModifyQuestionCaseDtoReq dtoReq);
    
    /**
     * 修改测试用例
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> modifyQuestionCase(ModifyQuestionCaseDtoReq dtoReq);
    
    /**
     * 删除测试用例
     *
     * @param questionCaseId 测试用例 ID
     */
    CommonDtoResult<Boolean> delQuestionCase(Long questionCaseId);
    
    /**
     * 获取测试用例
     *
     * @param questionId 问题 ID
     */
    List<QuestionCaseDtoResult> allQuestionCase(Long questionId);
    
}
