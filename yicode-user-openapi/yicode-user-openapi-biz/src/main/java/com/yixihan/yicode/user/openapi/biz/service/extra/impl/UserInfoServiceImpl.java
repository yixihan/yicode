package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.UserLanguageVO;
import com.yixihan.yicode.user.openapi.biz.feign.question.label.LabelUserFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserInfoFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserLanguageFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserWebsiteFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户资料 服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:46
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    @Resource
    private UserInfoFeignClient infoFeignClient;
    
    @Resource
    private UserWebsiteFeignClient websiteFeignClient;
    
    @Resource
    private UserLanguageFeignClient languageFeignClient;
    
    @Resource
    private LabelUserFeignClient labelUserFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public UserInfoVO modifyInfo(ModifyUserInfoReq req) {
        Long userId = userService.getUserId ();
    
        // 如果网站列表不为空, 则修改网站列表
        if (CollectionUtil.isNotEmpty (req.getUserWebsiteList ())) {
            modifyUserWebsite (userId, req.getUserWebsiteList ());
        }
    
        // 更新用户资料
        ModifyUserInfoDtoReq dtoReq = BeanUtil.toBean (req, ModifyUserInfoDtoReq.class);
        dtoReq.setUserId (userId);
        infoFeignClient.modifyInfo (dtoReq);
        
        return getUserInfo (userId);
    }
    
    @Override
    public UserInfoVO getUserInfo(Long userId) {
        // 获取用户资料
        UserInfoDtoResult dtoResult = infoFeignClient.getUserInfo (userId).getResult ();
        UserInfoVO userInfoVO = BeanUtil.toBean (dtoResult, UserInfoVO.class);
    
        // 获取用户网战
        List<UserWebsiteDtoResult> websiteDtoResultList = websiteFeignClient.getUserWebsite (userId).getResult ();
        
        if (CollectionUtil.isNotEmpty (websiteDtoResultList)) {
            userInfoVO.setUserWebsiteList (websiteDtoResultList.stream ()
                    .map (UserWebsiteDtoResult::getUserWebsite)
                    .collect (Collectors.toList ()));
        }
    
        // 获取用户语言
        List<UserLanguageDtoResult> languageDtoResults = languageFeignClient.getUserLanguage (userId).getResult ();
    
        if (CollectionUtil.isNotEmpty (languageDtoResults)) {
            userInfoVO.setUserLanguageList (BeanUtil.copyToList (languageDtoResults, UserLanguageVO.class));
        }
        
        // 获取用户标签
        List<LabelDtoResult> labelDtoResult = labelUserFeignClient.userLabelDetail (userId).getResult ();

        if (CollectionUtil.isNotEmpty (labelDtoResult)) {
            userInfoVO.setUserLabel (labelDtoResult.stream ()
                    .map (LabelDtoResult::getLabelName).collect(Collectors.toList()));
        }
    
        return userInfoVO;
    }
    
    /**
     * 修改用户网战列表
     *
     * @param userId 用户 ID
     * @param websiteList 用户网站列表
     */
    private void modifyUserWebsite (Long userId, List<String> websiteList) {
        // 获取用户现有网站列表
        List<String> oldWebSiteList = websiteFeignClient.getUserWebsite (userId).getResult ()
                .stream ()
                .map (UserWebsiteDtoResult::getUserWebsite)
                .collect (Collectors.toList ());
        
        // 过滤已有网站列表
        List<String> addWebSiteList = websiteList.stream ()
                .filter (o -> !oldWebSiteList.contains (o))
                .collect(Collectors.toList());
        List<String> delWebSiteList = oldWebSiteList.stream ()
                .filter (o -> !websiteList.contains (o))
                .collect(Collectors.toList());
    
        // 新增
        websiteFeignClient.addUserWebsite (new ModifyUserWebsiteDtoReq (userId, addWebSiteList));
        // 删除
        websiteFeignClient.delUserWebsite (new ModifyUserWebsiteDtoReq (userId, delWebSiteList));
    }
}
