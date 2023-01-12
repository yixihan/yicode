package com.yixihan.yicode.question.biz.service.label;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.dal.pojo.label.Label;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface LabelService extends IService<Label> {
    
    /**
     * 添加标签
     *
     * @param labelName 标签名
     */
    CommonDtoResult<Boolean> addLabel(String labelName);
    
    /**
     * 删除标签
     *
     * @param labelId 标签 ID
     */
    CommonDtoResult<Boolean> delLabel(Long labelId);
    
    /**
     * 获取标签
     *
     * @param labelIdList 标签 ID
     */
    List<LabelDtoResult> labelDetail(List<Long> labelIdList);
}