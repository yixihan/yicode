package com.yixihan.yicode.user.openapi.web.controller.extra;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.extra.UserFavoriteOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.*;
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
    public JsonResponse<CommonVO<Boolean>> addFavorite(AddFavoriteReq req) {
        return JsonResponse.ok (service.addFavorite (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> modifyFavorite(ModifyFavoriteReq req) {
        return JsonResponse.ok (service.modifyFavorite (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delFavorite(Long favoriteId) {
        return JsonResponse.ok (service.delFavorite (favoriteId));
    }
    
    @Override
    public JsonResponse<CommonVO<Integer>> getFavoriteCount(Long userId) {
        return JsonResponse.ok (service.getFavoriteCount (userId));
    }
    
    @Override
    public JsonResponse<PageVO<FavoriteVO>> getFavorites(FavoriteQueryReq req) {
        return JsonResponse.ok (service.getFavorites (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addCollection(ModifyCollectionReq req) {
        return JsonResponse.ok (service.addCollection (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delCollection(ModifyCollectionReq req) {
        return JsonResponse.ok (service.delCollection (req));
    }
    
    @Override
    public JsonResponse<PageVO<CollectionVO>> getCollections(CollectionQueryReq req) {
        return JsonResponse.ok (service.getCollections (req));
    }
}
