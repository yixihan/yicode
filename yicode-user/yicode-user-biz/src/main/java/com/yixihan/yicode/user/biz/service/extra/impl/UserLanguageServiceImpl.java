package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserLanguageService;
import com.yixihan.yicode.user.dal.mapper.extra.UserLanguageMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserLanguage;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        UserLanguage language = BeanUtil.toBean (dtoReq, UserLanguage.class);
        
        int modify = baseMapper.insert (language);
        if (modify != 1) {
            return new CommonDtoResult<> (
                    Boolean.FALSE,
                    BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ()
            );
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        UpdateWrapper<UserLanguage> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserLanguage.USER_ID, dtoReq.getUserId ())
                .eq (UserLanguage.LANGUAGE, dtoReq.getLanguage ())
                .set (UserLanguage.DEAL_COUNT, dtoReq.getDealCount ());
        int modify = baseMapper.update (null, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (
                    Boolean.FALSE,
                    BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ()
            );
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<UserLanguageDtoResult> getUserLanguage(Long userId) {
        QueryWrapper<UserLanguage> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserLanguage.USER_ID, userId);
        List<UserLanguage> values = Optional.ofNullable (baseMapper.selectList (wrapper)).orElse (Collections.emptyList ());
        return CopyUtils.copyMulti (UserLanguageDtoResult.class, values);
    }
}
