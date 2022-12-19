package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserWebsiteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserWebsiteMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserWebsite;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户个人网站表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserWebsiteServiceImpl extends ServiceImpl<UserWebsiteMapper, UserWebsite> implements UserWebsiteService {

    @Override
    public CommonDtoResult<Boolean> addUserLanguage(ModifyUserWebsiteDtoReq dtoReq) {
        if (StrUtil.isBlank (dtoReq.getUserWebsite ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空");
        }
        
        UserWebsite website = new UserWebsite ();
        website.setUserWebsite (dtoReq.getUserWebsite ());
        website.setUserId (dtoReq.getUserId ());
    
        int insert = baseMapper.insert (website);
        if (insert == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Boolean> modifyUserLanguage(ModifyUserWebsiteDtoReq dtoReq) {
        if (StrUtil.isBlank (dtoReq.getUserWebsite ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空");
        }
    
        UpdateWrapper<UserWebsite> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, dtoReq.getUserId ())
                .set (UserWebsite.USER_WEBSITE, dtoReq.getUserWebsite ());
        int update = baseMapper.update (null, wrapper);
        if (update == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Boolean> delUserLanguage(ModifyUserWebsiteDtoReq dtoReq) {
        if (StrUtil.isBlank (dtoReq.getUserWebsite ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空");
        }
    
        QueryWrapper<UserWebsite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, dtoReq.getUserId ())
                .eq (UserWebsite.USER_WEBSITE, dtoReq.getUserWebsite ());
    
        int delete = baseMapper.delete (wrapper);
        if (delete == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public List<UserWebsiteDtoResult> getUserLanguage(Long userId) {
        QueryWrapper<UserWebsite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, userId);
    
        List<UserWebsite> values = Optional.ofNullable (baseMapper.selectList (wrapper))
                .orElse (Collections.emptyList ());
        
        return CopyUtils.copyMulti (UserWebsiteDtoResult.class, values);
    }
}
