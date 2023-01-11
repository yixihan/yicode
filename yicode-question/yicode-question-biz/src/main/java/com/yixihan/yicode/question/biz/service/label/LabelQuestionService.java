package com.yixihan.yicode.question.biz.service.label;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
     * @param questionLabelName 标签名
     */
    CommonDtoResult<Boolean> addQuestionLabel(String questionLabelName);
    
    /**
     * 删除问题标签
     *
     * @param questionLabelId 标签 ID
     */
    CommonDtoResult<Boolean> delQuestionLabel(Long questionLabelId);
    
    /**
     * 获取问题标签
     *
     * @param questionLabelIdList 标签 ID
     */
    List<LabelDtoResult> questionLabelDetail(List<Long> questionLabelIdList);
}
