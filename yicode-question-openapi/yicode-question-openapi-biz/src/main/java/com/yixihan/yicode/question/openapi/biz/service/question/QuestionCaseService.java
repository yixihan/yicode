package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCaseVO;

import java.util.List;

/**
 * 问题测试用例管理 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface QuestionCaseService {
    
    /**
     * 添加测试用例
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> addQuestionCase(ModifyQuestionCaseReq req);
    
    /**
     * 修改测试用例
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> modifyQuestionCase(ModifyQuestionCaseReq req);
    
    /**
     * 删除测试用例
     *
     * @param id 测试用例 ID
     */
    CommonVO<Boolean> delQuestionCase(Long id);
    
    /**
     * 获取问题测试用例
     *
     * @param questionId 请求参数
     */
    List<QuestionCaseVO> allQuestionCase(Long questionId);
}
