package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.api.enums.AnswerTypeEnums;
import com.yixihan.yicode.question.api.enums.QuestionDifficultyTypeEnums;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
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
    public CommonVO<Boolean> addQuestion(ModifyQuestionReq req) {
        // 参数校验
        if (!QuestionDifficultyTypeEnums.contains (req.getQuestionDifficulty ()) ||
                StrUtil.isBlank (req.getQuestionName ()) ||
                StrUtil.isBlank (req.getQuestionDesc ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 构建请求 body
        ModifyQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionDtoReq.class);
        
        // 添加题目
        CommonDtoResult<Boolean> dtoResult = questionFeignClient.addQuestion (dtoReq).getResult ();
    
        // 如果添加失败, 抛出异常
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> modifyQuestion(ModifyQuestionReq req) {
        // 参数校验
        if (!questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (!QuestionDifficultyTypeEnums.contains (req.getQuestionDifficulty ()) ||
                StrUtil.isBlank (req.getQuestionName ()) ||
                StrUtil.isBlank (req.getQuestionDesc ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        ModifyQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyQuestionDtoReq.class);
    
        // 修改题目
        CommonDtoResult<Boolean> dtoResult = questionFeignClient.modifyQuestion (dtoReq).getResult ();
    
        // 如果修改失败, 抛出异常
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delQuestion(List<Long> questionIdList) {
        // 参数校验
        if (CollectionUtil.isEmpty (questionIdList)) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 删除题目
        CommonDtoResult<Boolean> dtoResult = questionFeignClient.delQuestion (questionIdList).getResult ();
    
        // 如果删除失败, 抛出异常
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> likeQuestion(LikeReq req) {
        // 参数校验 (id)
        if (!questionFeignClient.verifyQuestion (req.getSourceId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 获取点赞人用户 ID
        Long userId = userService.getUser ().getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (QUESTION_LIKE_KEY, req.getSourceId (), userId);
    
        if (!req.getLike ()) {
            // 取消点赞
            // 本身未点赞
            if (!isLike) {
                throw new BizException ("您已经取消点赞了");
            }
            // 更新 redis
            Integer likeCount = likeService.unLike (QUESTION_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = questionFeignClient.likeQuestion (dtoReq).getResult ();
            
            if (!dtoResult.getData ()) {
                throw new BizException (dtoResult.getMessage ());
            }
            return CommonVO.create (dtoResult);
        } else {
            // 点赞
            // 本身已点赞
            if (isLike) {
                throw new BizException ("您已经点赞了");
            }
            // 更新 redis
            Integer likeCount = likeService.like (QUESTION_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = questionFeignClient.likeQuestion (dtoReq).getResult ();
        
            if (!dtoResult.getData ()) {
                throw new BizException (dtoResult.getMessage ());
            }
            return CommonVO.create (dtoResult);
        }
    }
    
    @Override
    public CommonVO<Boolean> collectionQuestion(ModifyCollectionReq req) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹类型
        VerifyFavoriteTypeDtoReq verifyFavoriteTypeDtoReq = new VerifyFavoriteTypeDtoReq (
                req.getFavoriteId (),
                AnswerTypeEnums.QUESTION.getType ()
        );
        if (!favoriteFeignClient.verifyFavoriteType (verifyFavoriteTypeDtoReq).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 校验收藏内容 ID
        if (!questionFeignClient.verifyQuestion (req.getCollectionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userId);
        
        // 收藏内容
        CommonDtoResult<Boolean> dtoResult = collectionFeignClient.addCollection (dtoReq).getResult ();
    
        // 如果操作失败, 排除异常原因
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> cancelCollectionQuestion(ModifyCollectionReq req) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹类型
        VerifyFavoriteTypeDtoReq verifyFavoriteTypeDtoReq = new VerifyFavoriteTypeDtoReq (
                req.getFavoriteId (),
                AnswerTypeEnums.QUESTION.getType ()
        );
        if (!favoriteFeignClient.verifyFavoriteType (verifyFavoriteTypeDtoReq).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 校验收藏内容 ID
        if (!questionFeignClient.verifyQuestion (req.getCollectionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userId);
        
        // 取消收藏内容
        CommonDtoResult<Boolean> dtoResult = collectionFeignClient.delCollection (dtoReq).getResult ();
    
        // 如果操作失败, 排除异常原因
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> addQuestionLabel(ModifyLabelQuestionReq req) {
        // 校验参数
        if (!labelFeignClient.verifyLabel (req.getLabelId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (!questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构造请求 body
        ModifyLabelQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyLabelQuestionDtoReq.class);
    
        // 添加题解标签
        CommonDtoResult<Boolean> dtoResult = labelQuestionFeignClient.addQuestionLabel (dtoReq).getResult ();
    
        // 如果操作失败, 排除异常原因
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delQuestionLabel(ModifyLabelQuestionReq req) {
        // 校验参数
        if (!labelFeignClient.verifyLabel (req.getLabelId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (!questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构造请求 body
        ModifyLabelQuestionDtoReq dtoReq = BeanUtil.toBean (req, ModifyLabelQuestionDtoReq.class);
    
        // 添加题解标签
        CommonDtoResult<Boolean> dtoResult = labelQuestionFeignClient.delQuestionLabel (dtoReq).getResult ();
    
        // 如果操作失败, 排除异常原因
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public QuestionDetailVO questionDetail(Long questionId) {
        // 参数校验
        if (!questionFeignClient.verifyQuestion (questionId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 获取问题明细
        QuestionDetailDtoResult dtoResult = questionFeignClient.questionDetail (questionId).getResult ();
    
        return BeanUtil.toBean (dtoResult, QuestionDetailVO.class);
    }
    
    @Override
    public PageVO<QuestionVO> queryQuestion(QueryQuestionReq req) {
        // 构建请求 body
        QueryQuestionDtoReq dtoReq = BeanUtil.toBean (req, QueryQuestionDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        
        // 搜索问题
        PageDtoResult<QuestionDtoResult> dtoResult = questionFeignClient.queryQuestion (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, QuestionVO.class)
        );
    }
}
