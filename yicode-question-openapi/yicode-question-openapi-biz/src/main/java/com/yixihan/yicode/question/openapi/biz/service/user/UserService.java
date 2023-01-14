package com.yixihan.yicode.question.openapi.biz.service.user;

import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;

import java.util.List;

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
     * @param userIdList 用户 ID
     */
    List<UserCommonDtoResult> getUserCommonInfo (List<Long> userIdList);
    
    /**
     * 校验用户 ID 是否存在
     *
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifyUserId(Long userId);
}
