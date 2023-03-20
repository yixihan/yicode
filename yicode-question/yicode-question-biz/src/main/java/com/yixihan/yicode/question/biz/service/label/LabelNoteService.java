package com.yixihan.yicode.question.biz.service.label;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.dal.pojo.label.LabelNote;

import java.util.List;

/**
 * <p>
 * 题解标签表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface LabelNoteService extends IService<LabelNote> {
    
    /**
     * 添加题解标签
     *
     * @param dtoReq 请求参数
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> addNoteLabel(ModifyLabelNoteDtoReq dtoReq);
    
    /**
     * 删除题解标签
     *
     * @param dtoReq 请求参数
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> delNoteLabel(ModifyLabelNoteDtoReq dtoReq);
    
    /**
     * 获取题解标签
     *
     * @param noteId 标签 ID
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> noteLabelDetail(Long noteId);
    
    /**
     * 获取所有题解的标签
     *
     * @return {@link LabelDtoResult}
     */
    List<LabelDtoResult> allNoteLabel();
}
