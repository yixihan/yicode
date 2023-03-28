package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCaseVO;

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
     * @return {@link QuestionCaseVO}
     */
    QuestionCaseVO addQuestionCase(ModifyQuestionCaseReq req);
    
    /**
     * 修改测试用例
     *
     * @param req 请求参数
     * @return {@link QuestionCaseVO}
     */
    QuestionCaseVO modifyQuestionCase(ModifyQuestionCaseReq req);
    
    /**
     * 删除测试用例
     *
     * @param id 测试用例 ID
     */
    void delQuestionCase(Long id);
    
    /**
     * 获取问题测试用例
     *
     * @param req 请求参数
     * @return {@link QuestionCaseVO}
     */
    PageVO<QuestionCaseVO> allQuestionCase(QueryQuestionCaseReq req);
}
