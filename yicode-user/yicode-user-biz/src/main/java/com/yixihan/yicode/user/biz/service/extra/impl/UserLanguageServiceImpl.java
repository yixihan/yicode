package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserLanguageService;
import com.yixihan.yicode.user.dal.mapper.extra.UserLanguageMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户语言表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserLanguageServiceImpl extends ServiceImpl<UserLanguageMapper, UserLanguage> implements UserLanguageService {

    @Override
    public CommonDtoResult<Boolean> addUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> modifyUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        return null;
    }

    @Override
    public List<UserLanguageDtoResult> getUserLanguage(Long userId) {
        return null;
    }
}
