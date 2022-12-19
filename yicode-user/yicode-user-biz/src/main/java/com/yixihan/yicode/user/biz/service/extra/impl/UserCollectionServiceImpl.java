package com.yixihan.yicode.user.biz.service.extra.impl;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.dal.pojo.extra.UserCollection;
import com.yixihan.yicode.user.dal.mapper.extra.UserCollectionMapper;
import com.yixihan.yicode.user.biz.service.extra.UserCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-28
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Override
    public CommonDtoResult<Boolean> addCollection(ModifyCollectionDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> delCollection(ModifyCollectionDtoReq dtoReq) {
        return null;
    }

    @Override
    public <T> PageDtoResult<T> getCollections(CollectionQueryDtoReq dtoReq) {
        return null;
    }
}