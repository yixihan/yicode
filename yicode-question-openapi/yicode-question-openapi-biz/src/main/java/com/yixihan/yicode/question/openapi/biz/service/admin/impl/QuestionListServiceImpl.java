package com.yixihan.yicode.question.openapi.biz.service.admin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.enums.user.FavoriteTypeEnums;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyListQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.QuestionListVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserCollectionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserFavoriteFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.admin.QuestionListService;
import com.yixihan.yicode.user.api.dto.request.extra.*;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题单 服务实现类
 *
 * @author yixihan
 * @date 2023/3/9 9:43
 */
@Slf4j
@Service
public class QuestionListServiceImpl implements QuestionListService {
    
    @Resource
    private UserFavoriteFeignClient favoriteFeignClient;
    
    @Resource
    private UserCollectionFeignClient collectionFeignClient;
    
    private static final Long USER_ID = 1L;
    
    @Override
    public CommonVO<Boolean> createQuestionList(ModifyQuestionListReq req) {
        AddFavoriteDtoReq dtoReq = new AddFavoriteDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteType (FavoriteTypeEnums.QUESTION.name ());
        dtoReq.setFavoriteName (req.getQuestionListName ());
        dtoReq.setFavoriteBg (req.getQuestionListBg ());
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.addFavorite (dtoReq).getResult ();
        
        if (Boolean.FALSE.equals (dtoResult.getData ())) {
            throw new BizException (dtoResult.getMessage ());
        }
        
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> ModifyQuestionListReq(ModifyQuestionListReq req) {
        ModifyFavoriteDtoReq dtoReq = new ModifyFavoriteDtoReq ();
        dtoReq.setFavoriteId (req.getId ());
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteName (req.getQuestionListName ());
        dtoReq.setFavoriteBg (req.getQuestionListBg ());
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.modifyFavorite (dtoReq).getResult ();
    
        if (Boolean.FALSE.equals (dtoResult.getData ())) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delQuestionList(Long id) {
        ModifyFavoriteDtoReq dtoReq = new ModifyFavoriteDtoReq ();
        dtoReq.setFavoriteId (id);
        dtoReq.setUserId (USER_ID);
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.delFavorite (dtoReq).getResult ();
    
        if (Boolean.FALSE.equals (dtoResult.getData ())) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> addListQuestion(ModifyListQuestionReq req) {
        ModifyCollectionDtoReq dtoReq = new ModifyCollectionDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteId (req.getId ());
        dtoReq.setCollectionId (req.getQuestionId ());
        CommonDtoResult<Boolean> dtoResult = collectionFeignClient.addCollection (dtoReq).getResult ();
    
        if (Boolean.FALSE.equals (dtoResult.getData ())) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delListQuestion(ModifyListQuestionReq req) {
        ModifyCollectionDtoReq dtoReq = new ModifyCollectionDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteId (req.getId ());
        dtoReq.setCollectionId (req.getQuestionId ());
        CommonDtoResult<Boolean> dtoResult = collectionFeignClient.delCollection (dtoReq).getResult ();
    
        if (Boolean.FALSE.equals (dtoResult.getData ())) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<QuestionListVO> questionListPage(PageReq req) {
        FavoriteQueryDtoReq dtoReq = BeanUtil.toBean (req, FavoriteQueryDtoReq.class);
        dtoReq.setUserId (USER_ID);
        
        PageDtoResult<FavoriteDtoResult> dtoResult = favoriteFeignClient.getFavorites (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, QuestionListVO.class)
        );
    }
    
    @Override
    public List<QuestionListVO> questionListList() {
        FavoriteQueryDtoReq dtoReq = new FavoriteQueryDtoReq ();
        dtoReq.setPage (1L);
        dtoReq.setPageSize (10000L);
        dtoReq.setUserId (USER_ID);
    
        PageDtoResult<FavoriteDtoResult> dtoResult = favoriteFeignClient.getFavorites (dtoReq).getResult ();
    
        return BeanUtil.copyToList (dtoResult.getRecords (), QuestionListVO.class);
    }
    
    @Override
    public PageVO<QuestionVO> questionPage(Long id) {
        CollectionQueryDtoReq dtoReq = new CollectionQueryDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteId (id);
        PageDtoResult<CollectionDtoResult> dtoResult = collectionFeignClient.getCollections (dtoReq).getResult ();
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, QuestionVO.class)
        );
    }
}
