package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionCaseDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionCaseDtoReq;
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
     * @return {@link QuestionCaseDtoResult}
     */
    QuestionCaseDtoResult addQuestionCase(ModifyQuestionCaseDtoReq dtoReq);
    
    /**
     * 修改测试用例
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionCaseDtoResult}
     */
    QuestionCaseDtoResult modifyQuestionCase(ModifyQuestionCaseDtoReq dtoReq);
    
    /**
     * 删除测试用例
     *
     * @param id 测试用例 ID
     */
    void delQuestionCase(Long id);
    
    /**
     * 获取测试用例-列表
     *
     * @param questionId 问题 ID
     * @return {@link QuestionCaseDtoResult}
     */
    List<QuestionCaseDtoResult> allQuestionCaseList(Long questionId);
    
    /**
     * 获取测试用例-分页
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionCaseDtoResult}
     */
    PageDtoResult<QuestionCaseDtoResult> allQuestionCasePage(QueryQuestionCaseDtoReq dtoReq);
    
    /**
     * 校验测试用例
     *
     * @param id 测试用例 ID
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifyQuestionCase(Long id);
}
