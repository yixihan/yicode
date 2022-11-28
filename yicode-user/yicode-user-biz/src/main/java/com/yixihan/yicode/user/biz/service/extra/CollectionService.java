package com.yixihan.yicode.user.biz.service.extra;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.dal.pojo.extra.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户收藏表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-28
 */
public interface CollectionService extends IService<Collection> {

    /**
     * 收藏内容
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addCollection(ModifyCollectionDtoReq dtoReq);

    /**
     * 取消收藏内容
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> delCollection(ModifyCollectionDtoReq dtoReq);

    /**
     * 获取收藏夹内容
     *
     * @param dtoReq 请求参数
     */
    <T> PageDtoResult<T> getCollections(CollectionQueryDtoReq dtoReq);
}
