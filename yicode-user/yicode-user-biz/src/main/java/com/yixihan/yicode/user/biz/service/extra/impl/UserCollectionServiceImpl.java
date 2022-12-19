package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserCollectionService;
import com.yixihan.yicode.user.dal.mapper.extra.UserCollectionMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserCollection;
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
        UserCollection collection = CopyUtils.copySingle (UserCollection.class, dtoReq);
        int insert = baseMapper.insert (collection);
        if (insert == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Boolean> delCollection(ModifyCollectionDtoReq dtoReq) {
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserCollection.COLLECTION_ID, dtoReq.getCollectionId ())
                .eq (UserCollection.FAVORITE_ID, dtoReq.getFavoriteId ());
        int delete = baseMapper.delete (wrapper);
        if (delete == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public PageDtoResult<CollectionDtoResult> getCollections(CollectionQueryDtoReq dtoReq) {
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserCollection.FAVORITE_ID, dtoReq.getFavoriteId ());
        Page<UserCollection> values = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                wrapper);
        
        return PageUtil.pageToPageDtoResult (
                values,
                (o) -> CopyUtils.copySingle (CollectionDtoResult.class, o)
        );
    }
}
