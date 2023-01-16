package com.yixihan.yicode.user.biz.service.extra.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.dal.mapper.extra.UserInfoMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
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
        UserInfo oldInfo = baseMapper.selectOne (new QueryWrapper<UserInfo> ()
                .eq (UserInfo.USER_ID, dtoReq.getUserId ()));
        
        UserInfo info = BeanUtil.toBean (dtoReq, UserInfo.class);
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserInfo.USER_ID, info.getUserId ());
        info.setVersion (oldInfo.getVersion ());
        
        int modify = baseMapper.update (info, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (
                    Boolean.FALSE,
                    BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ()
            );
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public UserInfoDtoResult getUserInfo(Long userId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserInfo.USER_ID, userId);
        UserInfo info = Optional.ofNullable (baseMapper.selectOne (wrapper)).orElse (new UserInfo ());
    
        return BeanUtil.toBean (info, UserInfoDtoResult.class);
    }
    
    @Override
    public List<UserInfoDtoResult> getUserInfoList(List<Long> userIdList) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<> ();
        wrapper.in (UserInfo.USER_ID, userIdList);
        List<UserInfo> infoList = baseMapper.selectList (wrapper);
    
        return BeanUtil.copyToList (infoList, UserInfoDtoResult.class);
    }
}
