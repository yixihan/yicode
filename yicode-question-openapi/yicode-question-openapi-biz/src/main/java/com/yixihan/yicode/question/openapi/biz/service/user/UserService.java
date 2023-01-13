package com.yixihan.yicode.question.openapi.biz.service.user;

import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;

/**
 * 用户 服务类
 *
 * @author yixihan
 * @date 2023/1/13 17:18
 */
public interface UserService {
    
    /**
     * 获取登录用户信息
     *
     */
    UserDtoResult getUser ();
    
    /**
     * 获取指定 ID 用户信息
     *
     * @param userId 用户 ID
     */
    UserDtoResult getUser (Long userId);
    
    /**
     * 获取用户通用信息 (用户 ID, 用户名, 用户头像)
     *
     * @param userId 用户 ID
     */
    UserCommonDtoResult getUserCommonInfo (Long userId);
}
