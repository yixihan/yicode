package com.yixihan.yicode.user.biz.service.extra;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 修改用户个人资料
     *
     * @param dtoReq 请求参数
     */
    void modifyInfo(ModifyUserInfoDtoReq dtoReq);

    /**
     * 获取用户个人资料
     *
     * @param userId 用户 ID
     * @return {@link UserInfoDtoResult}
     */
    UserInfoDtoResult getUserInfo(Long userId);
    
    /**
     * 获取用户个人资料
     *
     * @param userIdList 用户 ID
     * @return {@link UserInfoDtoResult}
     */
    List<UserInfoDtoResult> getUserInfoList(List<Long> userIdList);
}
