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
    public CommonDtoResult<Boolean> addUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        if (dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空");
        }
        
        for (String item : dtoReq.getUserWebsite ()) {
            UserWebsite website = new UserWebsite ();
            website.setUserWebsite (item);
            website.setUserId (dtoReq.getUserId ());
            int insert = baseMapper.insert (website);
            if (insert != 1) {
                return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
            }
        }
    
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> modifyUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        if (dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空");
        }
    
        UpdateWrapper<UserWebsite> wrapper = new UpdateWrapper<> ();
        for (String item : dtoReq.getUserWebsite ()) {
            wrapper.eq (UserWebsite.USER_ID, dtoReq.getUserId ())
                    .set (UserWebsite.USER_WEBSITE, item);
            int update = baseMapper.update (null, wrapper);
            if (update != 1) {
                return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
            }
        }

        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> delUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        if (dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空");
        }
    
        QueryWrapper<UserWebsite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, dtoReq.getUserId ())
                .in (UserWebsite.USER_WEBSITE, dtoReq.getUserWebsite ());
    
        int delete = baseMapper.delete (wrapper);
        if (delete == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public List<UserWebsiteDtoResult> getUserWebsite(Long userId) {
        QueryWrapper<UserWebsite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, userId);
    
        List<UserWebsite> values = Optional.ofNullable (baseMapper.selectList (wrapper))
                .orElse (Collections.emptyList ());
        
        return CopyUtils.copyMulti (UserWebsiteDtoResult.class, values);
    }
}
