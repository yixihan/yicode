package com.yixihan.yicode.question.openapi.biz.service.user.impl;

import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.question.openapi.biz.feign.user.base.UserFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserInfoFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 17:20
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserFeignClient userFeignClient;
    
    @Resource
    private UserInfoFeignClient infoFeignClient;
    
    @Resource
    private HttpServletRequest request;
    
    @Override
    public UserDtoResult getUser() {
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER)
                .substring (AuthConstant.TOKEN_SUB_INDEX);
        return userFeignClient.getUserByToken (token).getResult ();
    }
    
    @Override
    public UserDtoResult getUser(Long userId) {
        return userFeignClient.getUserByUserId (userId).getResult ();
    }
    
    @Override
    public UserCommonDtoResult getUserCommonInfo(Long userId) {
        UserDtoResult userDtoResult = userFeignClient.getUserByUserId (userId).getResult ();
        UserInfoDtoResult infoDtoResult = infoFeignClient.getUserInfo (userId).getResult ();
    
        return new UserCommonDtoResult (userId, userDtoResult.getUserName (), infoDtoResult.getUserAvatar ());
    }
}
