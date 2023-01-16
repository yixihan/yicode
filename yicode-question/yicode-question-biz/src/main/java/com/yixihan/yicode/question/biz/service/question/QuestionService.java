package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.Question;

import java.util.List;

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
     */
    CommonDtoResult<Boolean> addQuestion(ModifyQuestionDtoReq dtoReq);
    
    /**
     * 修改问题
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> modifyQuestion(ModifyQuestionDtoReq dtoReq);
    
    /**
     * 删除问题
     *
     * @param questionIdList 问题 ID
     */
    CommonDtoResult<Boolean> delQuestion(List<Long> questionIdList);
    
    /**
     * 点赞问题
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> likeQuestion(LikeDtoReq dtoReq);
    
    /**
     * 问题明细
     *
     * @param questionId 问题 ID
     */
    QuestionDetailDtoResult questionDetail(Long questionId);
    
    /**
     * 搜索问题
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<QuestionDtoResult> queryQuestion(QueryQuestionDtoReq dtoReq);
    
    /**
     * 校验问题是否存在
     *
     * @param questionId 问题 ID
     * @return 存在 : true | 不存在 : false
     */
    CommonDtoResult<Boolean> verifyQuestion(Long questionId);
}
