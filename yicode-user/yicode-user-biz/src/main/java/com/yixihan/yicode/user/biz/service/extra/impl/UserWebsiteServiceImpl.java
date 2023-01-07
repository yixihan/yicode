package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.ArrayList;
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
        if (CollectionUtil.isEmpty (dtoReq.getUserWebsite ()) ||
                dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空!");
        }
        
        List<UserWebsite> websiteList = new ArrayList<> (dtoReq.getUserWebsite ().size ());
        for (String item : dtoReq.getUserWebsite ()) {
            UserWebsite website = new UserWebsite ();
            website.setUserWebsite (item);
            website.setUserId (dtoReq.getUserId ());
            websiteList.add (website);
        }
        boolean modify = this.saveBatch (websiteList);
        if (!modify) {
            return new CommonDtoResult<> (
                    Boolean.FALSE,
                    BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ()
            );
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> delUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        if (CollectionUtil.isEmpty (dtoReq.getUserWebsite ()) ||
                dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            return new CommonDtoResult<> (Boolean.FALSE, "个人网址信息为空!");
        }
    
        QueryWrapper<UserWebsite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, dtoReq.getUserId ())
                .in (UserWebsite.USER_WEBSITE, dtoReq.getUserWebsite ());
    
        int modify = baseMapper.delete (wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (
                    Boolean.FALSE,
                    BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ()
            );
        }
        return new CommonDtoResult<> (Boolean.TRUE);
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
