package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.question.AnswerTypeEnums;
import com.yixihan.yicode.common.enums.question.QuestionDifficultyTypeEnums;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCountDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCountVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelQuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserCollectionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserFavoriteFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.LikeService;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.VerifyFavoriteTypeDtoReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.QUESTION_LIKE_KEY;

/**
 * 问题 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:24
 */
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private LikeService likeService;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Resource
    private UserCollectionFeignClient collectionFeignClient;
    
    @Resource
    private UserFavoriteFeignClient favoriteFeignClient;
    
    @Resource
    private LabelFeignClient labelFeignClient;
    
    @Resource
    private LabelQuestionFeignClient labelQuestionFeignClient;
    
    @Override
    public QuestionDetailVO addQuestion(ModifyQuestionReq req) {
        // 参数校验
        Assert.isTrue (QuestionDifficultyTypeEnums.contains (req.getQuestionDifficulty ()));
        Assert.isFalse (StrUtil.isBlank (req.getQuestionName ()));

        // 构建请求 body
        ModifyQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionDtoReq.class);
        
        // 添加题目
        QuestionDetailDtoResult dtoResult = questionFeignClient.addQuestion (dtoReq).getResult ();

        return BeanUtil.toBean (dtoResult, QuestionDetailVO.class);
    }
    
    @Override
    public QuestionDetailVO modifyQuestion(ModifyQuestionReq req) {
        // 参数校验
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ());
        Assert.isTrue (QuestionDifficultyTypeEnums.contains (req.getQuestionDifficulty ()));
        Assert.isFalse (StrUtil.isBlank (req.getQuestionName ()));
        Assert.isFalse (StrUtil.isBlank (req.getQuestionDesc ()));
        
        // 构建请求 body
        ModifyQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionDtoReq.class);
    
        // 修改题目
        QuestionDetailDtoResult dtoResult = questionFeignClient.modifyQuestion (dtoReq).getResult ();
    
        return BeanUtil.toBean (dtoResult, QuestionDetailVO.class);
    }
    
    @Override
    public void delQuestion(List<Long> questionIdList) {
        // 参数校验
        Assert.isFalse (CollectionUtil.isEmpty (questionIdList));
        
        // 删除题目
       questionFeignClient.delQuestion (questionIdList);
    }
    
    @Override
    public void likeQuestion(LikeReq req) {
        // 参数校验 (id)
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getSourceId ()).getResult ());
        // 获取点赞人用户 ID
        Long userId = userService.getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (QUESTION_LIKE_KEY, req.getSourceId (), userId);
    
        if (Boolean.FALSE.equals (req.getLike ())) {
            // 取消点赞
            // 本身未点赞
            Assert.isTrue (isLike, new BizException ("您已经取消点赞了"));

            // 更新 redis
            Integer likeCount = likeService.unLike (QUESTION_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);questionFeignClient.likeQuestion (dtoReq);
        } else {
            // 点赞
            // 本身已点赞
            Assert.isFalse (isLike, new BizException ("您已经点赞了"));
            
            // 更新 redis
            Integer likeCount = likeService.like (QUESTION_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            questionFeignClient.likeQuestion (dtoReq);
        }
    }
    
    @Override
    public void collectionQuestion(ModifyCollectionReq req) {
        // 校验收藏夹类型
        VerifyFavoriteTypeDtoReq verifyFavoriteTypeDtoReq = new VerifyFavoriteTypeDtoReq (
                req.getFavoriteId (),
                AnswerTypeEnums.QUESTION.getType ()
        );
        Assert.isTrue (favoriteFeignClient.verifyFavoriteType (verifyFavoriteTypeDtoReq).getResult ());
        // 校验收藏内容 ID
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getCollectionId ()).getResult ());
    
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 收藏内容
        collectionFeignClient.addCollection (dtoReq);
    }
    
    @Override
    public void cancelCollectionQuestion(ModifyCollectionReq req) {
        // 校验收藏夹类型
        VerifyFavoriteTypeDtoReq verifyFavoriteTypeDtoReq = new VerifyFavoriteTypeDtoReq (
                req.getFavoriteId (),
                AnswerTypeEnums.QUESTION.getType ()
        );
        Assert.isTrue (favoriteFeignClient.verifyFavoriteType (verifyFavoriteTypeDtoReq).getResult ());
        // 校验收藏内容 ID
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getCollectionId ()).getResult ());

        
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 取消收藏内容
        collectionFeignClient.delCollection (dtoReq);
    }
    
    @Override
    public List<LabelVO> addQuestionLabel(ModifyLabelQuestionReq req) {
        // 校验参数
        Assert.isTrue (labelFeignClient.verifyLabel (req.getLabelId ()).getResult ());
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ());
    
        // 构造请求 body
        ModifyLabelQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyLabelQuestionDtoReq.class);
    
        // 添加题解标签
        List<LabelDtoResult> dtoResult = labelQuestionFeignClient.addQuestionLabel (dtoReq).getResult ();
        
        return BeanUtil.copyToList (dtoResult, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> delQuestionLabel(ModifyLabelQuestionReq req) {
        // 校验参数
        Assert.isTrue (labelFeignClient.verifyLabel (req.getLabelId ()).getResult ());
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ());
    
        // 构造请求 body
        ModifyLabelQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyLabelQuestionDtoReq.class);
    
        // 添加题解标签
        List<LabelDtoResult> dtoResult = labelQuestionFeignClient.delQuestionLabel (dtoReq).getResult ();
        
        return BeanUtil.copyToList (dtoResult, LabelVO.class);
    }
    
    @Override
    public QuestionDetailVO questionDetail(Long questionId) {
        // 参数校验
        Assert.isTrue (questionFeignClient.verifyQuestion (questionId).getResult ());
        
        // 获取问题明细
        QuestionDetailDtoReq dtoReq = new QuestionDetailDtoReq ();
        dtoReq.setQuestionId (questionId);
        dtoReq.setUserId (userService.getUserId ());
        QuestionDetailDtoResult dtoResult = questionFeignClient.questionDetail (dtoReq).getResult ();
        dtoResult.setQuestionDifficulty (QuestionDifficultyTypeEnums.valueOf (dtoResult.getQuestionDifficulty ()).getDescription ());
    
        return BeanUtil.toBean (dtoResult, QuestionDetailVO.class);
    }
    
    @Override
    public PageVO<QuestionVO> queryQuestion(QueryQuestionReq req) {
        // 构建请求 body
        QueryQuestionDtoReq dtoReq = BeanUtil.toBean (req, QueryQuestionDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 搜索问题
        PageDtoResult<QuestionDtoResult> dtoResult = questionFeignClient.queryQuestion (dtoReq).getResult ();
        dtoResult.getRecords ().forEach (o ->
            o.setQuestionDifficulty (QuestionDifficultyTypeEnums.valueOf (o.getQuestionDifficulty ()).getDescription ())
        );
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, QuestionVO.class)
        );
    }
    
    @Override
    public QuestionCountVO questionCount() {
        QuestionCountDtoResult dtoResult = questionFeignClient.questionCount ().getResult ();
        
        return BeanUtil.toBean (dtoResult, QuestionCountVO.class);
    }
}
