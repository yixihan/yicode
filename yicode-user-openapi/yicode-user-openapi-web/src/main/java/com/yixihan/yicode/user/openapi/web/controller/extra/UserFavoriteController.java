package com.yixihan.yicode.user.openapi.web.controller.extra;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.extra.UserFavoriteOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.AddFavoriteReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.CollectionQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FavoriteQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFavoriteReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.CollectionVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FavoriteVO;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户收藏 前端控制器
 *
 * @author yixihan
 * @date 2022/12/21 9:40
 */
@Slf4j
@RestController
public class UserFavoriteController implements UserFavoriteOpenApi {
    
    @Resource
    private UserFavoriteService service;
    
    @Override
    public JsonResponse<FavoriteVO> addFavorite(AddFavoriteReq req) {
        return JsonResponse.ok (service.addFavorite (req));
    }
    
    @Override
    public JsonResponse<FavoriteVO> modifyFavorite(ModifyFavoriteReq req) {
        return JsonResponse.ok (service.modifyFavorite (req));
    }
    
    @Override
    public void delFavorite(Long favoriteId) {
        service.delFavorite (favoriteId);
    }
    
    @Override
    public JsonResponse<Integer> getFavoriteCount(Long userId) {
        return JsonResponse.ok (service.getFavoriteCount (userId));
    }
    
    @Override
    public JsonResponse<PageVO<FavoriteVO>> getFavorites(FavoriteQueryReq req) {
        return JsonResponse.ok (service.getFavorites (req));
    }
    
    @Override
    public JsonResponse<PageVO<CollectionVO>> getCollections(CollectionQueryReq req) {
        return JsonResponse.ok (service.getCollections (req));
    }
}
