package com.yixihan.yicode.user.openapi.biz.service.extra;

import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;

/**
 * 用户资料 服务类
 *
 * @author yixihan
 * @date 2022/12/21 9:44
 */
public interface UserInfoService {
    
    /**
     * 更新用户资料
     *
     * @param req 请求参数
     */
    UserInfoVO modifyInfo(ModifyUserInfoReq req);
    
    /**
     * 获取用户资料
     *
     * @param userId 用户 ID
     */
    UserInfoVO getUserInfo(Long userId);
}
