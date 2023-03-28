package com.yixihan.yicode.question.openapi.biz.service.admin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.enums.user.FavoriteTypeEnums;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyListQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.QueryQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.QuestionListVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserCollectionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserFavoriteFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.admin.QuestionListService;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionService;
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
    
    @Resource
    private QuestionService questionService;
    
    private static final Long USER_ID = 1L;
    
    @Override
    public QuestionListVO createQuestionList(ModifyQuestionListReq req) {
        AddFavoriteDtoReq dtoReq = new AddFavoriteDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteType (FavoriteTypeEnums.QUESTION.name ());
        dtoReq.setFavoriteName (req.getQuestionListName ());
        dtoReq.setFavoriteBg (req.getQuestionListBg ());
        
        // 保存
        FavoriteDtoResult dtoResult = favoriteFeignClient.addFavorite (dtoReq).getResult ();
        
        return BeanUtil.toBean (dtoResult, QuestionListVO.class);
    }
    
    @Override
    public QuestionListVO modifyQuestionListReq(ModifyQuestionListReq req) {
        ModifyFavoriteDtoReq dtoReq = new ModifyFavoriteDtoReq ();
        dtoReq.setFavoriteId (req.getId ());
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteName (req.getQuestionListName ());
        dtoReq.setFavoriteBg (req.getQuestionListBg ());
        
        // 更新
        FavoriteDtoResult dtoResult = favoriteFeignClient.modifyFavorite (dtoReq).getResult ();
        
        return BeanUtil.toBean (dtoResult, QuestionListVO.class);
    }
    
    @Override
    public void delQuestionList(Long id) {
        favoriteFeignClient.delFavorite (id);
    }
    
    @Override
    public QuestionVO addListQuestion(ModifyListQuestionReq req) {
        ModifyCollectionDtoReq dtoReq = new ModifyCollectionDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteId (req.getId ());
        dtoReq.setCollectionId (req.getQuestionId ());
        
        // 添加
        collectionFeignClient.addCollection (dtoReq);
        
        return questionService.questionDetail (req.getQuestionId ());
    }
    
    @Override
    public void delListQuestion(ModifyListQuestionReq req) {
        ModifyCollectionDtoReq dtoReq = new ModifyCollectionDtoReq ();
        dtoReq.setUserId (USER_ID);
        dtoReq.setFavoriteId (req.getId ());
        dtoReq.setCollectionId (req.getQuestionId ());
        
        // 删除
        collectionFeignClient.delCollection (dtoReq);
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
        PageReq req = new PageReq ();
        req.setPage (1L);
        req.setPageSize (100000L);
        req.setSearchCount (Boolean.FALSE);
    
        return questionListPage (req).getRecords ();
    }
    
    @Override
    public QuestionListVO questionListDetail(Long id) {
        FavoriteDtoResult dtoResult = favoriteFeignClient.getFavoriteDetail (id).getResult ();
        
        return BeanUtil.toBean (dtoResult, QuestionListVO.class);
    }
    
    @Override
    public PageVO<QuestionVO> questionPage(QueryQuestionListReq req) {
        CollectionQueryDtoReq dtoReq = BeanUtil.toBean (req, CollectionQueryDtoReq.class);
        PageDtoResult<CollectionDtoResult> dtoResult = collectionFeignClient.collectionsDetailPage (dtoReq).getResult ();
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> questionService.questionDetail (o.getCollectionId ())
        );
    }
}
