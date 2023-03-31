package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCountVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;

import java.util.List;

/**
 * 问题 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface QuestionService {
    
    /**
     * 添加问题
     *
     * @param req 请求参数
     * @return {@link QuestionDetailVO}
     */
    QuestionDetailVO addQuestion(ModifyQuestionReq req);
    
    /**
     * 修改问题
     *
     * @param req 请求参数
     * @return {@link QuestionDetailVO}
     */
    QuestionDetailVO modifyQuestion(ModifyQuestionReq req);
    
    /**
     * 删除问题
     *
     * @param questionIdList 问题 ID
     */
    void delQuestion(List<Long> questionIdList);
    
    /**
     * 点赞问题
     *
     * @param req 请求参数
     */
    void likeQuestion(LikeReq req);
    
    /**
     * 收藏问题
     *
     * @param req 请求参数
     */
    void collectionQuestion(ModifyCollectionReq req);
    
    /**
     * 取消收藏问题
     *
     * @param req 请求参数
     */
    void cancelCollectionQuestion(ModifyCollectionReq req);
    
    /**
     * 修改题解标签
     *
     * @param req 请求参数
     * @return {@link LabelVO}
     */
    List<LabelVO> modifyQuestionLabel(ModifyLabelQuestionReq req);
    
    /**
     * 问题明细
     *
     * @param questionId 问题 ID
     * @return {@link QuestionDetailVO}
     */
    QuestionDetailVO questionDetail(Long questionId);
    
    /**
     * 搜索问题
     *
     * @param req 请求参数
     * @return {@link QuestionVO}
     */
    PageVO<QuestionVO> queryQuestion(QueryQuestionReq req);
    
    /**
     * 随机一题
     *
     * @return {@link QuestionDetailVO}
     */
    QuestionDetailVO randomQuestion();
    
    /**
     * 获取题目数量
     *
     * @return {@link QuestionCountVO}
     */
    QuestionCountVO questionCount();
}
