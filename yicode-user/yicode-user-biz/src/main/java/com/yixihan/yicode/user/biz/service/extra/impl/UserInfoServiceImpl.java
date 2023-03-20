package com.yixihan.yicode.user.biz.service.extra.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.dal.mapper.extra.UserInfoMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
    public void modifyInfo(ModifyUserInfoDtoReq dtoReq) {
        UserInfo info = BeanUtil.toBean (dtoReq, UserInfo.class);
        
        // 获取乐观锁
        Integer version = lambdaQuery ()
                .select (UserInfo::getVersion)
                .eq (UserInfo::getUserId, dtoReq.getUserId ())
                .one ()
                .getVersion ();
        Assert.notNull (version, BizCodeEnum.ACCOUNT_NOT_FOUND);
        info.setVersion (version);
    
        // 构造条件构造器
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserInfo.USER_ID, info.getUserId ());
        
        // 更新
        Assert.isTrue (update (info, wrapper), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    public UserInfoDtoResult getUserInfo(Long userId) {
        UserInfo info = lambdaQuery ()
                .eq (UserInfo::getUserId, userId)
                .one ();
        
        Assert.notNull (info, BizCodeEnum.ACCOUNT_NOT_FOUND);
    
        return BeanUtil.toBean (info, UserInfoDtoResult.class);
    }
    
    @Override
    public List<UserInfoDtoResult> getUserInfoList(List<Long> userIdList) {
        List<UserInfo> infoList = lambdaQuery ()
                .in (CollUtil.isNotEmpty (userIdList), UserInfo::getUserId, userIdList)
                .orderByDesc (UserInfo::getCreateTime)
                .list ();
        infoList = CollUtil.isEmpty (infoList) ? Collections.emptyList () : infoList;
    
        return BeanUtil.copyToList (infoList, UserInfoDtoResult.class);
    }
}
