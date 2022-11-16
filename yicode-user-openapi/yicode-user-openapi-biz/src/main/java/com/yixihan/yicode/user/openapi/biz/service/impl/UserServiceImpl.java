package com.yixihan.yicode.user.openapi.biz.service.impl;

import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.response.RoleVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserVO;
import com.yixihan.yicode.user.openapi.biz.feign.UserFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

;

/**
 * 用户 服务实现类
 *
 * @author yixihan
 * @date 2022-10-22-18:07
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public UserDetailInfoVO getUserInfo( Long userId) {
        UserDetailInfoDtoResult dtoResult = userFeignClient.getUserInfo (userId).getResult ();
        return getUserDetailInfoVO (dtoResult);
    }

    @Override
    public UserDetailInfoVO getUserInfo(HttpServletRequest request) {
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER).substring (AuthConstant.TOKEN_SUB_INDEX);
        UserDtoResult result = userFeignClient.getUserByToken (token).getResult ();
        UserDetailInfoDtoResult dtoResult = userFeignClient.getUserInfo (result.getUserId ()).getResult ();
        return getUserDetailInfoVO (dtoResult);
    }

    private UserDetailInfoVO getUserDetailInfoVO(UserDetailInfoDtoResult dtoResult) {
        UserVO userVO = CopyUtils.copySingle (UserVO.class, dtoResult.getUser ());
        List<RoleVO> roleVOList = CopyUtils.copyMulti (RoleVO.class, dtoResult.getUserRoleList ());
        UserInfoVO userInfoVO = CopyUtils.copySingle (UserInfoVO.class, dtoResult.getUserInfo ());
        return new UserDetailInfoVO (userVO, roleVOList, userInfoVO);
    }
}
