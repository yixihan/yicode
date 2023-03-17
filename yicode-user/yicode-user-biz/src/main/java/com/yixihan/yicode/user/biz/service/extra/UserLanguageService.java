package com.yixihan.yicode.user.biz.service.extra;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserLanguage;

import java.util.List;

/**
 * <p>
 * 用户语言表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
public interface UserLanguageService extends IService<UserLanguage> {

    /**
     * 添加用户语言
     *
     * @param dtoReq 请求参数
     * @return {@link UserLanguageDtoResult}
     */
    List<UserLanguageDtoResult> addUserLanguage(ModifyUserLanguageDtoReq dtoReq);

    /**
     * 更新用户语言
     *
     * @param dtoReq 请求参数
     * @return {@link UserLanguageDtoResult}
     */
    List<UserLanguageDtoResult> modifyUserLanguage(ModifyUserLanguageDtoReq dtoReq);

    /**
     * 获取用户语言列表
     *
     * @param userId 用户 ID
     * @return {@link UserLanguageDtoResult}
     */
    List<UserLanguageDtoResult> getUserLanguage(Long userId);

}
