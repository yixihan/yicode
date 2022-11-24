package com.yixihan.yicode.thirdpart.openapi.biz.service.user;

import com.yixihan.yicode.common.pojo.User;

/**
 * 用户模块 服务类
 *
 * @author yixihan
 * @date 2022/11/23 17:24
 */
public interface UserService {

    /**
     * 获取当前登录用户详细信息
     *
     * @return {@link User}
     */
    User getUserInfo();
}
