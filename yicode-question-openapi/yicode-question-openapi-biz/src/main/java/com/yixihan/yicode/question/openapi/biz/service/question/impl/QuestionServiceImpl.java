package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.QuestionCommentCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.ModifyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.openapi.api.enums.AnswerTypeEnums;
import com.yixihan.yicode.question.openapi.api.enums.QuestionDifficultyTypeEnums;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.comment.CommentFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.note.NoteFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.LikeService;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
    private CommentFeignClient commentFeignClient;
    
    @Resource
    private NoteFeignClient noteFeignClient;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    /**
     * redis key : 点赞问题
     */
    private static final String QUESTION_LIKE_KEY = "like:question";
    
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
    public QuestionDetailVO questionDetail(Long questionId) {
        // 参数校验
        if (!questionFeignClient.verifyQuestion (questionId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 获取问题明细
        QuestionDetailDtoResult dtoResult = questionFeignClient.questionDetail (questionId).getResult ();
        setQuestionExtraInfo (dtoResult);
    
        return BeanUtil.toBean (dtoResult, QuestionDetailVO.class);
    }
    
    @Override
    public PageVO<QuestionVO> queryQuestion(QueryQuestionReq req) {
        // 构建请求 body
        QueryQuestionDtoReq dtoReq = BeanUtil.toBean (req, QueryQuestionDtoReq.class);
        
        // 搜索问题
        PageDtoResult<QuestionDtoResult> dtoResult = questionFeignClient.queryQuestion (dtoReq).getResult ();
    
        for (QuestionDtoResult item : dtoResult.getRecords ()) {
            ThreadUtil.execute (() -> setQuestionExtraInfo (item));
        }
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, QuestionVO.class)
        );
    }
    
    /**
     * 获取问题的额外信息<br>
     * 包括评论数, 题解数, 题目通过率
     *
     * @param dtoResult 问题
     */
    private void setQuestionExtraInfo(QuestionDtoResult dtoResult) {
        // 获取评论数和题解数
        QuestionCommentCountDtoReq dtoReq = new QuestionCommentCountDtoReq ();
        dtoReq.setAnswerId (dtoResult.getQuestionId ());
        dtoReq.setAnswerType (AnswerTypeEnums.QUESTION.getType ());
        dtoResult.setCommentCount (commentFeignClient.questionCommentCount (dtoReq).getResult ().getData ());
        
        // 获取题解数
        dtoResult.setNoteCount (noteFeignClient.questionNoteCount (dtoResult.getQuestionId ()).getResult ().getData ());
        
        // 计算通过率
        dtoResult.setPassRate (BigDecimal.valueOf (dtoResult.getSuccessCount () / dtoResult.getCommentCount ()));
    }
}
