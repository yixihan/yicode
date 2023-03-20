package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserWebsiteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserWebsiteMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserWebsite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void addUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        if (CollectionUtil.isEmpty (dtoReq.getUserWebsite ()) ||
                dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            throw new BizException ("个人网址信息为空!");
        }
        
        List<UserWebsite> websiteList = new ArrayList<> (dtoReq.getUserWebsite ().size ());
        for (String item : dtoReq.getUserWebsite ()) {
            UserWebsite website = new UserWebsite ();
            website.setUserWebsite (item);
            website.setUserId (dtoReq.getUserId ());
            websiteList.add (website);
        }
        
        // 保存
        Assert.isTrue (saveBatch (websiteList), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    public void delUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        if (CollUtil.isEmpty (dtoReq.getUserWebsite ()) ||
                dtoReq.getUserWebsite ().stream ().anyMatch (StrUtil::isBlank)) {
            throw new BizException ("个人网址信息为空!");
        }
    
        QueryWrapper<UserWebsite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserWebsite.USER_ID, dtoReq.getUserId ())
                .in (UserWebsite.USER_WEBSITE, dtoReq.getUserWebsite ());
    
        remove (wrapper);
    }

    @Override
    public List<UserWebsiteDtoResult> getUserWebsite(Long userId) {
        List<UserWebsite> websiteList = lambdaQuery ()
                .eq (UserWebsite::getUserId, userId)
                .orderByDesc (UserWebsite::getCreateTime)
                .list ();
        websiteList = CollUtil.isEmpty (websiteList) ? Collections.emptyList () : websiteList;
        
        return BeanUtil.copyToList (websiteList, UserWebsiteDtoResult.class);
    }
}
