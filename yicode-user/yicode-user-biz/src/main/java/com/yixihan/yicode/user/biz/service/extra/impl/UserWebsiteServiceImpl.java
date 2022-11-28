package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserWebsiteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserWebsiteMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserWebsite;
import org.springframework.stereotype.Service;

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
    public CommonDtoResult<Boolean> addUserLanguage(ModifyUserWebsiteDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> modifyUserLanguage(ModifyUserWebsiteDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> delUserLanguage(ModifyUserWebsiteDtoReq dtoReq) {
        return null;
    }

    @Override
    public List<UserWebsiteDtoResult> getUserLanguage(Long userId) {
        return null;
    }
}
