package com.yixihan.yicode.question.openapi.biz.service.label;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.label.AddLabelReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;

import java.util.List;

/**
 * 标签 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface LabelService {
    
    /**
     * 添加新标签
     *
     * @param req 请求参数
     * @return {@link LabelVO}
     */
    LabelVO addLabel(AddLabelReq req);
    
    /**
     * 删除标签
     *
     * @param labelIdList 标签 ID
     */
    void delLabel(List<Long> labelIdList);
    
    /**
     * 获取标签明细
     *
     * @param labelIdList 标签 ID
     * @return {@link LabelVO}
     */
    List<LabelVO> labelDetail(List<Long> labelIdList);
    
    /**
     * 获取题解所有标签
     *
     * @param noteId 题解 ID
     * @return {@link LabelVO}
     */
    List<LabelVO> noteLabelDetail(Long noteId);
    
    /**
     * 获取问题所有标签
     *
     * @param questionId 问题 ID
     * @return {@link LabelVO}
     */
    List<LabelVO> questionLabelDetail(Long questionId);
    
    /**
     * 获取所有标签
     *
     * @param req 请求参数
     * @return {@link LabelVO}
     */
    PageVO<LabelVO> allLabel(PageReq req);
    
    /**
     * 获取所有题解标签
     * @return {@link LabelVO}
     */
    List<LabelVO> allNoteLabel();
    
    /**
     * 获取所有问题标签
     * @return {@link LabelVO}
     */
    List<LabelVO> allQuestionLabel();
}
