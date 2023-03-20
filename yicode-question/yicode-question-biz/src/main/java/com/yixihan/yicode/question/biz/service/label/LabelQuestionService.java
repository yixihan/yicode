package com.yixihan.yicode.question.biz.service.label;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.dal.pojo.label.LabelQuestion;

import java.util.List;

/**
 * <p>
 * 问题标签表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface LabelQuestionService extends IService<LabelQuestion> {
    
    /**
     * 添加问题标签
     *
     * @param dtoReq 请求参数
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> addQuestionLabel(ModifyLabelQuestionDtoReq dtoReq);
    
    /**
     * 删除问题标签
     *
     * @param dtoReq 请求参数
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> delQuestionLabel(ModifyLabelQuestionDtoReq dtoReq);
    
    /**
     * 获取问题标签
     *
     * @param questionId 标签 ID
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> questionLabelDetail(Long questionId);
    
    /**
     * 获取问题的所有标签
     *
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> allQuestionLabel();
}
