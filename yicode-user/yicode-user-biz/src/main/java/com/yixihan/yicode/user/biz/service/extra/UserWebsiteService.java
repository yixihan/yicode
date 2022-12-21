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
    CommonDtoResult<Boolean> addUserWebsite(ModifyUserWebsiteDtoReq dtoReq);

    /**
     * 修改用户个人网站
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> modifyUserWebsite(ModifyUserWebsiteDtoReq dtoReq);

    /**
     * 删除用户个人网站
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> delUserWebsite(ModifyUserWebsiteDtoReq dtoReq);
    
    /**
     * 获取用户网站列表
     *
     * @param userId 用户 ID
     */
    List<UserWebsiteDtoResult> getUserWebsite(Long userId);
}
