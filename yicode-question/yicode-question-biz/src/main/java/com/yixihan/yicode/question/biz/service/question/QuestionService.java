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
    
    CommonDtoResult<Boolean> addQuestion(ModifyQuestionDtoReq dtoReq);
    
    
    CommonDtoResult<Boolean> modifyQuestion(ModifyQuestionDtoReq dtoReq);
    
    
    CommonDtoResult<Boolean> delQuestion(List<Long> questionIdList);
    
    
    CommonDtoResult<Boolean> likeQuestion(LikeDtoReq dtoReq);
    
    
    QuestionDetailDtoResult questionDetail(Long questionId);
    
    
    PageDtoResult<QuestionDtoResult> queryQuestion(QueryQuestionDtoReq dtoReq);
}
