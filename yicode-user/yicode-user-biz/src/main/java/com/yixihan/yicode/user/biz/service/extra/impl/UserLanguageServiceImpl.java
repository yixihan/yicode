package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserLanguageService;
import com.yixihan.yicode.user.dal.mapper.extra.UserLanguageMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserLanguage;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public List<UserLanguageDtoResult> addUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        UserLanguage language = BeanUtil.toBean (dtoReq, UserLanguage.class);
    
        // 保存
        Assert.isTrue (save (language), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return getUserLanguage (dtoReq.getUserId ());
    }
    
    @Override
    public List<UserLanguageDtoResult> modifyUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        UpdateWrapper<UserLanguage> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserLanguage.USER_ID, dtoReq.getUserId ())
                .eq (UserLanguage.LANGUAGE, dtoReq.getLanguage ())
                .set (UserLanguage.DEAL_COUNT, dtoReq.getDealCount ());
    
        // 更新
        Assert.isTrue (update (wrapper), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return getUserLanguage (dtoReq.getUserId ());
    }
    
    @Override
    public List<UserLanguageDtoResult> getUserLanguage(Long userId) {
        List<UserLanguage> languageList =  lambdaQuery ()
                .eq (UserLanguage::getUserId, userId)
                .orderByDesc (UserLanguage::getDealCount)
                .list ();
        
        languageList = CollUtil.isEmpty (languageList) ? Collections.emptyList () : languageList;
        
        return BeanUtil.copyToList (languageList, UserLanguageDtoResult.class);
    }
}
