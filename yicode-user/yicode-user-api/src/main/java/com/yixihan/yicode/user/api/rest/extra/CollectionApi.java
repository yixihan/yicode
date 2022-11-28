package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用户收藏 api
 * </p>
 *
 * @author yixihan
 * @since 2022-11-28
 */
@Api(value = "用户收藏 api")
@RequestMapping("/collection/")
public interface CollectionApi {

    @ApiOperation ("收藏内容")
    @PostMapping(value = "/add")
    ApiResult<CommonDtoResult<Boolean>> addCollection (@RequestParam("favoriteId") Long favoriteId,
                                                       @RequestParam("collectionId") Long collectionId);

    @ApiOperation ("取消收藏内容")
    @PostMapping(value = "/del")
    ApiResult<CommonDtoResult<Boolean>> delCollection (@RequestParam("favoriteId") Long favoriteId,
                                                       @RequestParam("collectionId") Long collectionId);

    @ApiOperation("获取收藏夹内容")
    @PostMapping(value = "/del", produces = "application/json")
    <T> ApiResult<PageDtoResult<T>> getCollections(@RequestBody CollectionQueryDtoReq dtoReq);
}
