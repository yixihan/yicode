package com.yixihan.yicode.user.openapi.biz.service.impl;

import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.ResetPasswordDtoReq;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import com.yixihan.yicode.user.openapi.api.enums.LoginTypeEnums;
import com.yixihan.yicode.user.openapi.api.vo.request.*;
import com.yixihan.yicode.user.openapi.api.vo.response.RoleVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.UserVO;
import com.yixihan.yicode.user.openapi.biz.feign.CodeFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.EmailFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.SMSFeignClient;
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

    @Resource
    private CodeFeignClient codeFeignClient;

    @Resource
    private EmailFeignClient emailFeignClient;

    @Resource
    private SMSFeignClient smsFeignClient;

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

    @Override
    public CommonVO<Boolean> register(RegisterUserReq req) {
        // 合法性校验
        if (LoginTypeEnums.USERNAME_PASSWORD.getType ().equals (req.getType ())) {
            // 用户名+密码注册
            // TODO 校验用户名是否重复
            // TODO 创建用户
        } else if (LoginTypeEnums.EMAIL_CODE.getType ().equals (req.getType ())) {
            // 邮箱+验证码注册
            // TODO 校验邮箱是否已被绑定
            // TODO 校验验证码正确性
            // TODO 创建用户
        } else if (LoginTypeEnums.MOBILE_CODE.getType ().equals (req.getType ())) {
            // 手机号+验证码注册
            // TODO 校验手机号是否已被绑定
            // TODO 校验验证码正确性
            // TODO 创建用户
        } else {
            log.error ("错误的注册方式!", new BizException (800001, "错误的注册方式!"));
            return new CommonVO<> (false, "错误的注册方式!");
        }
        return null;
    }

    @Override
    public CommonVO<Boolean> resetPassword(ResetPasswordReq req) {
        ResetPasswordDtoReq dtoReq = CopyUtils.copySingle (ResetPasswordDtoReq.class, req);

        CommonDtoResult<Boolean> dtoResult = userFeignClient.resetPassword (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> bindEmail(HttpServletRequest request, BindEmailReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> unbindEmail(HttpServletRequest request) {
        return null;
    }

    @Override
    public CommonVO<Boolean> bindMobile(HttpServletRequest request, BindMobileReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> unbindMobile(HttpServletRequest request) {
        return null;
    }

    @Override
    public CommonVO<Boolean> resetUserName(HttpServletRequest request, ResetUserNameReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> cancellation(HttpServletRequest request) {
        return null;
    }

    private UserDetailInfoVO getUserDetailInfoVO(UserDetailInfoDtoResult dtoResult) {
        UserVO userVO = CopyUtils.copySingle (UserVO.class, dtoResult.getUser ());
        List<RoleVO> roleVOList = CopyUtils.copyMulti (RoleVO.class, dtoResult.getUserRoleList ());
        UserInfoVO userInfoVO = CopyUtils.copySingle (UserInfoVO.class, dtoResult.getUserInfo ());
        return new UserDetailInfoVO (userVO, roleVOList, userInfoVO);
    }
}
