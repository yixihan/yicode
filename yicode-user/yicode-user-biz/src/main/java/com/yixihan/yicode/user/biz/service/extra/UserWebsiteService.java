package com.yixihan.yicode.user.biz.service.extra;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserWebsite;

import java.util.List;

/**
 * <p>
 * 用户个人网站表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
public interface UserWebsiteService extends IService<UserWebsite> {

    /**
     * 添加用户个人网站
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addUserLanguage(ModifyUserWebsiteDtoReq dtoReq);

    /**
     * 修改用户个人网站
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> modifyUserLanguage(ModifyUserWebsiteDtoReq dtoReq);

    /**
     * 删除用户个人网站
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> delUserLanguage(ModifyUserWebsiteDtoReq dtoReq);

    /**
     * 获取加用户个人网站
     *
     * @param userId 用户 ID
     */
    List<UserWebsiteDtoResult> getUserLanguage(Long userId);
}
