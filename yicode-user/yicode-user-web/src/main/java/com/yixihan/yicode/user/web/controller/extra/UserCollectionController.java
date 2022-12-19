package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
    private UserCollectionService userCollectionService;

    @Override
    public ApiResult<CommonDtoResult<Boolean>> addCollection(ModifyCollectionDtoReq dtoReq) {
        return ApiResult.create (userCollectionService.addCollection (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> delCollection(ModifyCollectionDtoReq dtoReq) {
        return ApiResult.create (userCollectionService.delCollection (dtoReq));
    }

    @Override
    public ApiResult<PageDtoResult<CollectionDtoResult>> getCollections(CollectionQueryDtoReq dtoReq) {
        return ApiResult.create (userCollectionService.getCollections (dtoReq));
    }
}
