package com.yixihan.yicode.thirdpart.openapi.biz.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.pojo.User;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.user.user.UserFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.user.UserService;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块 服务实现类
 *
 * @author yixihan
 * @date 2022/11/23 17:24
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public User getUserInfo() {
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER).substring (AuthConstant.TOKEN_SUB_INDEX);
        UserDtoResult result = userFeignClient.getUserByToken (token).getResult ();
        return BeanUtil.toBean (result, User.class);
    }
}
