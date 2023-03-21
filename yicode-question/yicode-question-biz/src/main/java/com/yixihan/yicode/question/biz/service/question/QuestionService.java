package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.admin.BrokenDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.admin.CommitDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCountDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.Question;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问题表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface QuestionService extends IService<Question> {
    
    /**
     * 添加问题
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionDetailDtoResult}
     */
    QuestionDetailDtoResult addQuestion(ModifyQuestionDtoReq dtoReq);
    
    /**
     * 修改问题
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionDetailDtoResult}
     */
    QuestionDetailDtoResult modifyQuestion(ModifyQuestionDtoReq dtoReq);
    
    /**
     * 删除问题
     *
     * @param questionIdList 问题 ID
     */
    void delQuestion(List<Long> questionIdList);
    
    /**
     * 点赞问题
     *
     * @param dtoReq 请求参数
     */
    void likeQuestion(LikeDtoReq dtoReq);
    
    /**
     * 问题明细
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionDetailDtoResult}
     */
    QuestionDetailDtoResult questionDetail(QuestionDetailDtoReq dtoReq);
    
    /**
     * 搜索问题
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionDtoResult}
     */
    PageDtoResult<QuestionDtoResult> queryQuestion(QueryQuestionDtoReq dtoReq);
    
    /**
     * 校验问题是否存在
     *
     * @param questionId 问题 ID
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifyQuestion(Long questionId);
    
    /**
     * 更新问题评论数
     *
     * @param questionId 问题 ID
     * @param commentCount 评论数量
     */
    void modifyQuestionCommentCount (Long questionId, Integer commentCount);
    
    /**
     * 更新问题题解数
     *
     * @param questionId 问题 ID
     * @param noteCount 题解数量
     */
    void modifyQuestionNoteCount (Long questionId, Integer noteCount);
    
    /**
     * 更新问题提交数
     *
     * @param questionId 问题 ID
     * @param commitCount 提交数量
     */
    void modifyQuestionCommitCount (Long questionId, Integer commitCount);
    
    /**
     * 更新问题通过数
     *
     * @param questionId 问题 ID
     * @param successCount 通过数量
     */
    void modifyQuestionSuccessCount (Long questionId, Integer successCount);
    
    /**
     * 获取问题名字
     *
     * @param questionIdList 问题 ID
     * @return 问题 ID-问题名字
     */
    Map<Long, String> questionName(List<Long> questionIdList);
    
    /**
     * 管理中心-查看网址数据
     *
     * @param dtoReq 请求参数
     * @return {@link BrokenDataDtoResult}
     */
    Map<String, BrokenDataDtoResult> brokenData(AdminDataDtoReq dtoReq);
    
    /**
     * 管理中心-代码提交数据
     *
     * @param dtoReq 请求参数
     * @return {@link CommitDataDtoResult}
     */
    CommitDataDtoResult commitData(AdminDataDtoReq dtoReq);
    
    /**
     * 获取问题数量
     *
     * @return {@link QuestionCountDtoResult}
     */
    QuestionCountDtoResult questionCount();
}
