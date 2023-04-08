package com.yixihan.yicode.user.openapi.biz.service.extra;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.*;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.CollectionVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FavoriteVO;

/**
 * 用户收藏 服务类
 *
 * @author yixihan
 * @date 2022/12/21 9:44
 */
public interface UserFavoriteService {
    
    /**
     * 添加收藏夹
     *
     * @param req 请求参数
     * @return {@link FavoriteVO}
     */
    FavoriteVO addFavorite(AddFavoriteReq req);
    
    /**
     * 修改收藏夹
     *
     * @param req 请求参数
     * @return {@link FavoriteVO}
     */
    FavoriteVO modifyFavorite(ModifyFavoriteReq req);
    
    /**
     * 删除收藏夹
     *
     * @param favoriteId 收藏夹 ID
     */
    void delFavorite(Long favoriteId);
    
    /**
     * 获取收藏夹数量
     *
     * @param userId 用户 ID
     * @return 收藏夹数量
     */
    Integer getFavoriteCount(Long userId);
    
    /**
     * 获取用户收藏夹列表
     *
     * @param req 请求参数
     * @return {@link FavoriteVO}
     */
    PageVO<FavoriteVO> getFavorites(FavoriteQueryReq req);

    /**
     * 获取用户问题收藏夹列表
     *
     * @param req 请求参数
     * @return {@link FavoriteVO}
     */
    PageVO<FavoriteVO> getQuestionFavorites(FavoriteQueryReq req);

    /**
     * 获取用户题解收藏夹列表
     *
     * @param req 请求参数
     * @return {@link FavoriteVO}
     */
    PageVO<FavoriteVO> getNoteFavorites(FavoriteQueryReq req);

    /**
     * 获取收藏夹内所有内容
     *
     * @param req 请求参数
     * @return {@link CollectionVO}
     */
    PageVO<CollectionVO> getCollections(CollectionQueryReq req);

    /**
     * 取消收藏
     *
     * @param req 请求参数
     */
    void cancel(ModifyCollectionReq req);
}
