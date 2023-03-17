package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 用户收藏 api
 * </p>
 *
 * @author yixihan
 * @since 2022-11-28
 */
@Api(tags = "用户收藏 api")
@RequestMapping("/collection")
public interface UserCollectionApi {

    @ApiOperation ("收藏内容")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<List<CollectionDtoResult>> addCollection (@RequestBody ModifyCollectionDtoReq dtoReq);

    @ApiOperation ("取消收藏内容")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<List<CollectionDtoResult>> delCollection (@RequestBody ModifyCollectionDtoReq dtoReq);

    @ApiOperation("获取收藏夹内容-分页查询")
    @PostMapping(value = "/detail/page", produces = "application/json")
    ApiResult<PageDtoResult<CollectionDtoResult>> collectionsDetailPage(@RequestBody CollectionQueryDtoReq dtoReq);
    
    @ApiOperation("获取收藏夹内容-列表查询")
    @PostMapping(value = "/detail/list", produces = "application/json")
    ApiResult<List<CollectionDtoResult>> collectionsDetailList(@RequestBody Long favoriteId);
}
