package com.yixihan.yicode.user.biz.service.extra.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserLabelDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.biz.service.extra.UserLabelService;
import com.yixihan.yicode.user.biz.service.extra.UserLanguageService;
import com.yixihan.yicode.user.biz.service.extra.UserWebsiteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserInfoMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Resource
    private UserWebsiteService websiteService;
    
    @Resource
    private UserLanguageService languageService;
    
    @Resource
    private UserLabelService labelService;

    @Override
    public CommonDtoResult<Boolean> modifyInfo(ModifyUserInfoDtoReq dtoReq) {
        UserInfo info = BeanUtil.toBean (dtoReq, UserInfo.class);
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserInfo.USER_ID, info.getUserId ());
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
        UserInfo info = baseMapper.selectOne (wrapper);
        info = Optional.ofNullable (info).orElse (new UserInfo ());
    
        UserInfoDtoResult userInfoDtoResult = CopyUtils.copySingle (UserInfoDtoResult.class, info);
        userInfoDtoResult.setUserWebsiteList (getUserWebSiteList (userId));
        userInfoDtoResult.setUserLanguageList (getUserLanguageList (userId));
        userInfoDtoResult.setUserLanguageList (getUserLabelList (userId));
        return userInfoDtoResult;
    }
    
    
    /**
     * 获取用户网站列表
     *
     * @param userId 用户 ID
     * @return 用户网站列表
     */
    private List<String> getUserWebSiteList (Long userId) {
        List<UserWebsiteDtoResult> dtoResultList = websiteService.getUserWebsite (userId);
        
        return dtoResultList.stream ().map (UserWebsiteDtoResult::getUserWebsite)
                .collect (Collectors.toList ());
    }
    
    /**
     * 获取用户语言列表
     *
     * @param userId 用户 ID
     * @return 用户语言列表
     */
    private List<String> getUserLanguageList (Long userId) {
        List<UserLanguageDtoResult> dtoResultList = languageService.getUserLanguage (userId);
        
        return dtoResultList.stream ().map (UserLanguageDtoResult::getLanguage)
                .collect (Collectors.toList ());
    }
    
    /**
     * 获取用户语言列表
     *
     * @param userId 用户 ID
     * @return 用户语言列表
     */
    private List<String> getUserLabelList (Long userId) {
        List<UserLabelDtoResult> dtoResultList = labelService.getUserLabel (userId);
        
        return dtoResultList.stream ().map (UserLabelDtoResult::getLabelName)
                .collect (Collectors.toList ());
    }
}
