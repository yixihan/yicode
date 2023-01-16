package com.yixihan.yicode.question.biz.service.label;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelUserDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.dal.pojo.label.LabelUser;

import java.util.List;

/**
 * <p>
 * 用户标签表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface LabelUserService extends IService<LabelUser> {
    
    /**
     * 添加用户标签
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addUserLabel(ModifyLabelUserDtoReq dtoReq);
    
    /**
     * 删除用户标签
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> delUserLabel(ModifyLabelUserDtoReq dtoReq);
    
    /**
     * 获取用户标签
     *
     * @param userId 标签 ID
     */
    List<LabelDtoResult> userLabelDetail(Long userId);
}
