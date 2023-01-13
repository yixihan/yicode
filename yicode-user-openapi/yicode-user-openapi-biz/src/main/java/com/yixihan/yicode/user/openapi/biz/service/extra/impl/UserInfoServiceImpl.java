package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.UserLanguageVO;
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
    private UserService userService;
    
    @Override
    public CommonVO<Boolean> modifyInfo(ModifyUserInfoReq req) {
        if (req == null) {
            return new CommonVO<> ();
        }
        Boolean flag = Boolean.TRUE;
        Long userId = userService.getUser ().getUserId ();
    
        // 如果网站列表不为空, 则修改网站列表
        if (CollectionUtil.isNotEmpty (req.getUserWebsiteList ())) {
            flag = modifyUserWebsite (userId, req.getUserWebsiteList ());
        }
    
        ModifyUserInfoDtoReq dtoReq = CopyUtils.copySingle (ModifyUserInfoDtoReq.class, req);
        dtoReq.setUserId (userId);
        
        CommonDtoResult<Boolean> dtoResult = infoFeignClient.modifyInfo (dtoReq).getResult ();
        if (!dtoResult.getData () || !flag) {
            throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
        }
        return new CommonVO<> (Boolean.TRUE);
    }
    
    @Override
    public UserInfoVO getUserInfo(Long userId) {
        // 获取用户资料
        UserInfoDtoResult dtoResult = infoFeignClient.getUserInfo (userId).getResult ();
        UserInfoVO userInfoVO = CopyUtils.copySingle (UserInfoVO.class, dtoResult);
    
        // 获取用户网战
        userInfoVO.setUserWebsiteList (websiteFeignClient.getUserWebsite (userId).getResult ().
                stream ().map (UserWebsiteDtoResult::getUserWebsite).collect(Collectors.toList()));
    
        // 获取用户语言
        List<UserLanguageDtoResult> languageDtoResults = languageFeignClient.getUserLanguage (userId).getResult ();
        userInfoVO.setUserLanguageList (CopyUtils.copyMulti (UserLanguageVO.class, languageDtoResults));
        
        // TODO 获取用户标签
        
        
        return userInfoVO;
    }
    
    /**
     * 修改用户网战列表
     *
     * @param userId 用户 ID
     * @param websiteList 用户网站列表
     * @return true : 修改成功 | false : 修改失败
     */
    private Boolean modifyUserWebsite (Long userId, List<String> websiteList) {
        // 获取用户现有网站列表
        List<String> oldWebSiteList = websiteFeignClient.getUserWebsite (userId).getResult ()
                .stream ().map (UserWebsiteDtoResult::getUserWebsite).collect (Collectors.toList ());
        
        // 过滤已有网站列表
        List<String> addWebSiteList = websiteList.stream ()
                .filter ((o) -> !oldWebSiteList.contains (o)).collect(Collectors.toList());
        List<String> delWebSiteList = oldWebSiteList.stream ()
                .filter ((o) -> !websiteList.contains (o)).collect(Collectors.toList());
    
        CommonDtoResult<Boolean> addResult = websiteFeignClient
                .addUserWebsite (new ModifyUserWebsiteDtoReq (userId, addWebSiteList)).getResult ();
        CommonDtoResult<Boolean> delResult = websiteFeignClient
                .delUserWebsite (new ModifyUserWebsiteDtoReq (userId, delWebSiteList)).getResult ();
        return addResult.getData () && delResult.getData ();
    }
}
