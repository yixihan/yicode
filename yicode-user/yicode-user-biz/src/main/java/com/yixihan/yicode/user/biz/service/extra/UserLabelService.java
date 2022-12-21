package com.yixihan.yicode.user.biz.service.extra;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLabelDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLabelDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户标签表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-12-21
 */
public interface UserLabelService extends IService<UserLabel> {
    
    /**
     * 添加用户标签
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addUserLabel(ModifyUserLabelDtoReq dtoReq);
    
    /**
     * 获取用户标签
     *
     * @param userId 用户 ID
     */
    List<UserLabelDtoResult> getUserLabel(Long userId);
    
}
