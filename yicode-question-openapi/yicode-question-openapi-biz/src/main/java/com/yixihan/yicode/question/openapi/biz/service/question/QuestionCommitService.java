package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.question.AddQuestionNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserQuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRateVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CommitRecordVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionAnswerVO;

import java.util.List;

/**
 * 提交答案 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface QuestionCommitService {
    
    /**
     * 测试代码
     *
     * @param req 请求参数
     */
    void test(CodeReq req);
    
    /**
     * 提交代码
     *
     * @param req 请求参数
     */
    void commit(CodeReq req);
    
    /**
     * 备注问题提交记录
     *
     * @param req 请求参数
     * @return {@link QuestionAnswerVO}
     */
    QuestionAnswerVO addQuestionAnswerNote(AddQuestionNoteReq req);
    
    /**
     * 获取单个问题提交记录
     *
     * @param req 请求参数
     * @return {@link QuestionAnswerVO}
     */
    PageVO<QuestionAnswerVO> queryQuestionAnswer(QuestionAnswerReq req);
    
    /**
     * 获取用户问题提交记录
     *
     * @param req 请求参数
     * @return {@link QuestionAnswerVO}
     */
    PageVO<QuestionAnswerVO> queryUserQuestionAnswer(UserQuestionAnswerReq req);
    
    /**
     * 获取用户提交代码次数记录
     *
     * @param month 年月 yyyy-MM
     * @param userId 用户 id
     * @return month(yyyy-MM) - {@link QuestionAnswerDtoResult}
     */
    List<CommitRecordVO> codeCommitCount(String month, Long userId);
    
    /**
     * 获取用户做题进度
     *
     * @param userId 用户 id
     * @return {@link CodeRateVO}
     */
    CodeRateVO codeRate(Long userId);
}
