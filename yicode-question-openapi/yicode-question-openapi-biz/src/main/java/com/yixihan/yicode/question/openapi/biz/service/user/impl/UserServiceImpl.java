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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Long getUserId() {
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER)
                .substring (AuthConstant.TOKEN_SUB_INDEX);
        
        return userFeignClient.getUserIdByToken (token).getResult ();
    }
    
    @Override
    public UserDtoResult getUser() {
        return getUser (getUserId ());
    }
    
    @Override
    public UserDtoResult getUser(Long userId) {
        return userFeignClient.getUserByUserId (userId).getResult ();
    }
    
    @Override
    public List<UserCommonDtoResult> getUserCommonInfo(List<Long> userIdList) {
        List<UserDtoResult> userDtoResult = userFeignClient.getUserListByUserId (userIdList).getResult ();
        Map<Long, UserInfoDtoResult> infoDtoResult = infoFeignClient.getUserInfoList (userIdList).getResult ().stream ()
                .collect (Collectors.toMap (
                        UserInfoDtoResult::getUserId,
                        Function.identity (),
                        (key1, key2) -> key1
                ));
    
        List<UserCommonDtoResult> commonInfoList = new ArrayList<> (userDtoResult.size ());
        for (UserDtoResult dtoResult : userDtoResult) {
            commonInfoList.add (new UserCommonDtoResult (
                    dtoResult.getUserId (),
                    dtoResult.getUserName (),
                    infoDtoResult.get (dtoResult.getUserId ()).getUserAvatar ())
            );
        }
        
        return commonInfoList;
    }
    
    @Override
    public Boolean verifyUserId(Long userId) {
        UserDtoResult user = getUser (userId);
        return user != null && user.getUserId () != null;
    }
}
