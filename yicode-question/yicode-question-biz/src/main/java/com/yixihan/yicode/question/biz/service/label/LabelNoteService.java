package com.yixihan.yicode.question.biz.service.label;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
     * @param noteLabelName 标签名
     */
    CommonDtoResult<Boolean> addNoteLabel(String noteLabelName);
    
    /**
     * 删除题解标签
     *
     * @param noteLabelId 标签 ID
     */
    CommonDtoResult<Boolean> delNoteLabel(Long noteLabelId);
    
    /**
     * 获取题解标签
     *
     * @param noteLabelIdList 标签 ID
     */
    List<LabelDtoResult> noteLabelDetail(List<Long> noteLabelIdList);
}
