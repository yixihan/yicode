package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.user.FavoriteTypeEnums;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.extra.*;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.*;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.CollectionVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FavoriteVO;
import com.yixihan.yicode.user.openapi.biz.feign.question.note.NoteFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserCollectionFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserFavoriteFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户收藏 服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:45
 */
@Slf4j
@Service
public class UserFavoriteServiceImpl implements UserFavoriteService {
    
    @Resource
    private UserFavoriteFeignClient favoriteFeignClient;
    
    @Resource
    private UserCollectionFeignClient collectionFeignClient;
    
    @Resource
    private NoteFeignClient noteFeignClient;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public FavoriteVO addFavorite(AddFavoriteReq req) {
        // 校验收藏夹名 (不为空) & 收藏夹类型
        // 校验收藏夹 ID & 收藏夹名 (不为空) & 收藏数量
        Assert.isTrue (StrUtil.isNotBlank (req.getFavoriteName ()),
                new BizException ("收藏夹不能为空!"));
        Assert.isTrue (Boolean.FALSE.equals (FavoriteTypeEnums.contains (req.getFavoriteType ())),
                new BizException ("收藏夹类型错误!"));
        
        // 新增收藏夹
        AddFavoriteDtoReq dtoReq = BeanUtil.toBean (req, AddFavoriteDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        FavoriteDtoResult dtoResult = favoriteFeignClient.addFavorite (dtoReq).getResult ();
        return BeanUtil.toBean (dtoResult, FavoriteVO.class);
    }
    
    @Override
    public FavoriteVO modifyFavorite(ModifyFavoriteReq req) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹 ID & 收藏夹名 (不为空) & 收藏数量
        Assert.isFalse (favoriteFeignClient.verifyFavorite (req.getFavoriteId ()).getResult (),
                new BizException ("收藏夹不存在!"));
        Assert.isTrue (StrUtil.isNotBlank (req.getFavoriteName ()),
                new BizException ("收藏夹不能为空!"));
        
        
        // 修改收藏夹
        ModifyFavoriteDtoReq dtoReq = BeanUtil.toBean (req, ModifyFavoriteDtoReq.class);
        dtoReq.setUserId (userId);
        FavoriteDtoResult dtoResult = favoriteFeignClient.modifyFavorite (dtoReq).getResult ();
        return BeanUtil.toBean (dtoResult, FavoriteVO.class);
    }
    
    @Override
    public void delFavorite(Long favoriteId) {
        // 校验收藏夹 ID
        Assert.isFalse (favoriteFeignClient.verifyFavorite (favoriteId).getResult (),
                new BizException ("收藏夹不存在!"));
        
        // 删除收藏夹
        favoriteFeignClient.delFavorite (favoriteId);
    }
    
    @Override
    public Integer getFavoriteCount(Long userId) {
        return favoriteFeignClient.getFavoriteCount (userId).getResult ();
    }
    
    @Override
    public PageVO<FavoriteVO> getFavorites(FavoriteQueryReq req) {
        UserDtoResult user = userService.getUser (req.getUserId ());
        FavoriteQueryDtoReq dtoReq = BeanUtil.toBean (req, FavoriteQueryDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());

        PageDtoResult<FavoriteDtoResult> dtoResult = favoriteFeignClient.getFavorites (dtoReq).getResult ();
        dtoResult.getRecords ().parallelStream ().forEach (o -> o.setUserName (user.getUserName ()));
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, FavoriteVO.class)
        );
    }
    
    @Override
    public PageVO<CollectionVO> getCollections(CollectionQueryReq req) {
        CollectionQueryDtoReq dtoReq = BeanUtil.toBean (req, CollectionQueryDtoReq.class);
    
        PageDtoResult<CollectionDtoResult> dtoResult = collectionFeignClient.collectionsDetailPage (dtoReq).getResult ();
        FavoriteDtoResult favoriteDtoResult = favoriteFeignClient.getFavoriteDetail (req.getFavoriteId ()).getResult ();
    
        final Map<Long, String> nameMap;
        List<Long> collectionList = dtoResult.getRecords ().stream ().map (CollectionDtoResult::getCollectionId)
                .collect(Collectors.toList());
        
        if (FavoriteTypeEnums.QUESTION.getType ().equals (favoriteDtoResult.getFavoriteType ())) {
            nameMap = questionFeignClient.questionName (collectionList).getResult ();
        } else {
            nameMap = noteFeignClient.noteName (collectionList).getResult ();
        }
        
        dtoResult.getRecords ().forEach (o -> o.setCollectionName (nameMap.get (o.getCollectionId ())));
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, CollectionVO.class)
        );
    }
    
    @Override
    public void cancel(ModifyCollectionReq req) {
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        collectionFeignClient.delCollection (dtoReq);
    }
}
