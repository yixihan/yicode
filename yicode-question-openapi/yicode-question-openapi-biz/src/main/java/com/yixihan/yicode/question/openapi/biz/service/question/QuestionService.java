package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
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
     */
    CommonVO<Boolean> addQuestion(ModifyQuestionReq req);
    
    /**
     * 修改问题
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> modifyQuestion(ModifyQuestionReq req);
    
    /**
     * 删除问题
     *
     * @param questionIdList 问题 ID
     */
    CommonVO<Boolean> delQuestion(List<Long> questionIdList);
    
    /**
     * 点赞问题
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> likeQuestion(LikeReq req);
    
    /**
     * 收藏问题
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> collectionQuestion(ModifyCollectionReq req);
    
    /**
     * 取消收藏问题
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> cancelCollectionQuestion(ModifyCollectionReq req);
    
    /**
     * 添加题解标签
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> addQuestionLabel(ModifyLabelQuestionReq req);
    
    /**
     * 删除题解标签
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> delQuestionLabel(ModifyLabelQuestionReq req);
    
    /**
     * 问题明细
     *
     * @param questionId 问题 ID
     */
    QuestionDetailVO questionDetail(Long questionId);
    
    /**
     * 搜索问题
     *
     * @param req 请求参数
     */
    PageVO<QuestionVO> queryQuestion(QueryQuestionReq req);
}
