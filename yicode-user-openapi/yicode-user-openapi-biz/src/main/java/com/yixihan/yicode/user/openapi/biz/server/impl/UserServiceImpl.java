package com.yixihan.yicode.user.openapi.biz.server.impl;

import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.response.RoleVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserVO;
import com.yixihan.yicode.user.openapi.biz.feign.UserFeignClient;
import com.yixihan.yicode.user.openapi.biz.server.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yixihan
 * @date 2022-10-22-18:07
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public UserDetailInfoVO getUserInfo(Long userId) {
        UserDetailInfoDtoResult dtoResult = userFeignClient.getUserInfo (userId).getResult ();
        log.info ("dtoResult : {}", dtoResult);
        UserVO userVO = CopyUtils.copySingle (UserVO.class, dtoResult.getUser ());
        List<RoleVO> roleVOList = CopyUtils.copyMulti (RoleVO.class, dtoResult.getUserRoleList ());
        UserInfoVO userInfoVO = CopyUtils.copySingle (UserInfoVO.class, dtoResult.getUserInfo ());
        UserDetailInfoVO vo = new UserDetailInfoVO (userVO, roleVOList, userInfoVO);
        log.info ("vo : {}", vo);
        return vo;
    }
}
