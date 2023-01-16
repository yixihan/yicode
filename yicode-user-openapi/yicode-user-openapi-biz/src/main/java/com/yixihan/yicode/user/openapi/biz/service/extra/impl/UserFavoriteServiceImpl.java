package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.extra.*;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.openapi.api.enums.FavoriteTypeEnums;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.AddFavoriteReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.CollectionQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FavoriteQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFavoriteReq;
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
    public CommonVO<Boolean> addFavorite(AddFavoriteReq req) {
        // 校验收藏夹名 (不为空) & 收藏夹类型
        if (StrUtil.isBlank (req.getFavoriteName ())) {
            throw new BizException ("收藏夹不能为空!");
        }
        if (!FavoriteTypeEnums.contains (req.getFavoriteType ())) {
            throw new BizException ("收藏夹类型错误!");
        }
        
        // 新增收藏夹
        AddFavoriteDtoReq dtoReq = BeanUtil.toBean (req, AddFavoriteDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.addFavorite (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> modifyFavorite(ModifyFavoriteReq req) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹 ID & 收藏夹名 (不为空) & 收藏数量
        if (verifyFavoriteId (userId, req.getFavoriteId ())) {
            throw new BizException ("收藏夹不存在!");
        }
        if (StrUtil.isBlank (req.getFavoriteName ())) {
            throw new BizException ("收藏夹不能为空!");
        }
        
        // 修改收藏夹
        ModifyFavoriteDtoReq dtoReq = BeanUtil.toBean (req, ModifyFavoriteDtoReq.class);
        dtoReq.setUserId (userId);
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.modifyFavorite (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delFavorite(Long favoriteId) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹 ID
        if (verifyFavoriteId (userId, favoriteId)) {
            throw new BizException ("收藏夹不存在!");
        }
        
        // 删除收藏夹
        ModifyFavoriteDtoReq dtoReq = new ModifyFavoriteDtoReq ();
        dtoReq.setUserId (userId);
        dtoReq.setFavoriteId (favoriteId);
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.delFavorite (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Integer> getFavoriteCount(Long userId) {
        CommonDtoResult<Integer> dtoResult = favoriteFeignClient.getFavoriteCount (userId).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<FavoriteVO> getFavorites(FavoriteQueryReq req) {
        FavoriteQueryDtoReq dtoReq = BeanUtil.toBean (req, FavoriteQueryDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());

        PageDtoResult<FavoriteDtoResult> dtoResult = favoriteFeignClient.getFavorites (dtoReq).getResult ();
        dtoResult.getRecords ().forEach ((o) -> o.setUserName (userService.getUser ().getUserName ()));
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, FavoriteVO.class)
        );
    }
    
    @Override
    public PageVO<CollectionVO> getCollections(CollectionQueryReq req) {
        Long userId = userService.getUser ().getUserId ();
        CollectionQueryDtoReq dtoReq = BeanUtil.toBean (req, CollectionQueryDtoReq.class);
        dtoReq.setUserId (userId);
    
        PageDtoResult<CollectionDtoResult> dtoResult = collectionFeignClient.getCollections (dtoReq).getResult ();
        FavoriteDetailQueryDtoReq favoriteDetailQueryDtoReq = new FavoriteDetailQueryDtoReq (userId, req.getFavoriteId ());
        FavoriteDtoResult favoriteDtoResult = favoriteFeignClient.getFavoriteDetail (favoriteDetailQueryDtoReq).getResult ();
    
        final Map<Long, String> nameMap;
        List<Long> collectionList = dtoResult.getRecords ().stream ().map (CollectionDtoResult::getCollectionId)
                .collect(Collectors.toList());
        
        if (FavoriteTypeEnums.QUESTION.getType ().equals (favoriteDtoResult.getFavoriteType ())) {
            nameMap = questionFeignClient.questionName (collectionList).getResult ();
        } else {
            nameMap = noteFeignClient.noteName (collectionList).getResult ();
        }
        
        dtoResult.getRecords ().forEach ((o) -> o.setCollectionName (nameMap.get (o.getCollectionId ())));
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, CollectionVO.class)
        );
    }
    
    /**
     * 校验收藏夹 ID 是否存在
     *
     * @param userId 用户 ID
     * @param favoriteId 收藏夹 ID
     */
    private Boolean verifyFavoriteId (Long userId, Long favoriteId) {
        FavoriteDetailQueryDtoReq dtoReq = new FavoriteDetailQueryDtoReq (userId, favoriteId);
    
        FavoriteDtoResult dtoResult = favoriteFeignClient.getFavoriteDetail (dtoReq).getResult ();
        return dtoResult == null;
    }
}
