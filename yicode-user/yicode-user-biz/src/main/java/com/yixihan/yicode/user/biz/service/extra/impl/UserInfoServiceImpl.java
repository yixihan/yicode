package com.yixihan.yicode.user.biz.service.extra.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.dal.mapper.extra.UserInfoMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Override
    public CommonDtoResult<Boolean> modifyInfo(ModifyUserInfoDtoReq dtoReq) {
        UserInfo info = CopyUtils.copySingle (UserInfo.class, dtoReq);
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserInfo.USER_ID, info.getUserId ());
        int update = baseMapper.update (info, wrapper);
        if (update == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public UserInfoDtoResult getUserInfo(Long userId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserInfo.USER_ID, userId);
        UserInfo info = Optional.ofNullable (baseMapper.selectOne (wrapper))
                .orElse (new UserInfo ());
        
        return CopyUtils.copySingle (UserInfoDtoResult.class, info);
    }
}
