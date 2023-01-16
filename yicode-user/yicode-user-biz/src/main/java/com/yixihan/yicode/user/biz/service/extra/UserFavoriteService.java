package com.yixihan.yicode.user.biz.service.extra;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteDetailQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;

/**
 * <p>
 * 用户收藏夹表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
public interface UserFavoriteService extends IService<UserFavorite> {

    /**
     * 添加收藏夹
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addFavorite(AddFavoriteDtoReq dtoReq);

    /**
     * 修改收藏夹
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> modifyFavorite(ModifyFavoriteDtoReq dtoReq);

    /**
     * 删除收藏夹
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> delFavorite(ModifyFavoriteDtoReq dtoReq);

    /**
     * 获取收藏夹数量
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Integer> getFavoriteCount(Long userId);

    /**
     * 获取全部收藏夹
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<FavoriteDtoResult> getFavorites(FavoriteQueryDtoReq dtoReq);
    
    /**
     * 获取收藏夹详情
     *
     * @param dtoReq 请求参数
     */
    FavoriteDtoResult getFavoriteDetail(FavoriteDetailQueryDtoReq dtoReq);
    
    /**
     * 校验收藏夹是否存在
     *
     * @param favoriteId 收藏夹 ID
     * @return 存在 : true | 不存在 : false
     */
    CommonDtoResult<Boolean> verifyFavorite(Long favoriteId);
}
