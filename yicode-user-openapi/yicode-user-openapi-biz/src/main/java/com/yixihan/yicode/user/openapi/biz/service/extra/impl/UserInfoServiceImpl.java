package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserInfoFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserLanguageFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserWebsiteFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户资料 服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:46
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    @Resource
    private UserInfoFeignClient infoFeignClient;
    
    @Resource
    private UserWebsiteFeignClient websiteFeignClient;
    
    @Resource
    private UserLanguageFeignClient languageFeignClient;
    
    @Resource
    private UserService userService;
    
    
    @Override
    public CommonVO<Boolean> modifyInfo(ModifyUserInfoReq req) {
        return null;
    }
    
    @Override
    public UserInfoVO getUserInfo(Long userId) {
        return null;
    }
}
