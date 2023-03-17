package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserCollectionApi;
import com.yixihan.yicode.user.biz.service.extra.UserCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-11-28
 */
@Slf4j
@RestController
public class UserCollectionController implements UserCollectionApi {

    @Resource
    private UserCollectionService service;

    @Override
    public ApiResult<List<CollectionDtoResult>> addCollection(ModifyCollectionDtoReq dtoReq) {
        return ApiResult.create (service.addCollection (dtoReq));
    }

    @Override
    public ApiResult<List<CollectionDtoResult>> delCollection(ModifyCollectionDtoReq dtoReq) {
        return ApiResult.create (service.delCollection (dtoReq));
    }

    @Override
    public ApiResult<PageDtoResult<CollectionDtoResult>> collectionsDetailPage(CollectionQueryDtoReq dtoReq) {
        return ApiResult.create (service.collectionsDetailPage (dtoReq));
    }
    
    @Override
    public ApiResult<List<CollectionDtoResult>> collectionsDetailList(Long favoriteId) {
        return ApiResult.create (service.collectionsDetailList (favoriteId));
    }
}
