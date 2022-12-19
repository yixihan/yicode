package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
public interface UserCollectionApi {

    @ApiOperation ("收藏内容")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addCollection (@RequestBody ModifyCollectionDtoReq dtoReq);

    @ApiOperation ("取消收藏内容")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delCollection (@RequestBody ModifyCollectionDtoReq dtoReq);

    @ApiOperation("获取收藏夹内容")
    @PostMapping(value = "/get", produces = "application/json")
    ApiResult<PageDtoResult<CollectionDtoResult>> getCollections(@RequestBody CollectionQueryDtoReq dtoReq);
}
