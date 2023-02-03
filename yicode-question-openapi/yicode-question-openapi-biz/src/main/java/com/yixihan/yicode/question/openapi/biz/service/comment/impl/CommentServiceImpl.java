package com.yixihan.yicode.question.openapi.biz.service.comment.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddRootCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddSonCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.RootCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.SonCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.common.enums.question.AnswerTypeEnums;
import com.yixihan.yicode.question.openapi.api.vo.request.AddMessageReq;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddRootCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddSonCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.RootCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.SonCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.RootCommentDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.SonCommentDetailVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.comment.CommentFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.note.NoteFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.LikeService;
import com.yixihan.yicode.question.openapi.biz.service.comment.CommentService;
import com.yixihan.yicode.question.openapi.biz.service.message.UserMsgService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.ROOT_COMMENT_LIKE_KEY;
import static com.yixihan.yicode.common.constant.RedisKeyConstant.SON_COMMENT_LIKE_KEY;

/**
 * 评论 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:21
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private CommentFeignClient commentFeignClient;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Resource
    private NoteFeignClient noteFeignClient;
    
    @Resource
    private UserMsgService msgService;
    
    @Resource
    private LikeService likeService;
    
    @Override
    public CommonVO<Boolean> addRootComment(AddRootCommentReq req) {
        // 参数校验 (评论, 评论内容 ID)
        if (StrUtil.isBlank (req.getContent ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (AnswerTypeEnums.NOTE.getType ().equals (req.getAnswerType ()) &&
        !noteFeignClient.verifyNote (req.getAnswerId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (AnswerTypeEnums.QUESTION.getType ().equals (req.getAnswerType ()) &&
        !questionFeignClient.verifyQuestion (req.getAnswerId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        AddRootCommentDtoReq dtoReq = BeanUtil.toBean (req, AddRootCommentDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        
        // 添加父评论
        CommonDtoResult<Boolean> dtoResult = commentFeignClient.addRootComment (dtoReq).getResult ();
        
        // 添加失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        
        if (AnswerTypeEnums.NOTE.getType ().equals (req.getAnswerType ())) {
            // 发送消息
            AddMessageReq messageReq = new AddMessageReq ();
            messageReq.setMessageType (MsgTypeEnums.REPLY.getType ());
            messageReq.setSourceId (req.getAnswerId ());
            messageReq.setReceiveUseId (noteFeignClient.noteDetail (req.getAnswerId ())
                    .getResult ().getUserId ());
            msgService.addMessage (messageReq);
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> addSonComment(AddSonCommentReq req) {
        // 参数校验 (id 是否存在 & 评论内容不能为空)
        if (StrUtil.isBlank (req.getContent ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (!commentFeignClient.verifyRootComment (req.getRootId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构建请求 body
        AddSonCommentDtoReq dtoReq = BeanUtil.toBean (req, AddSonCommentDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        
        // 添加子评论
        CommonDtoResult<Boolean> dtoResult = commentFeignClient.addSonComment (dtoReq).getResult ();
    
        // 添加失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        
        // 添加成功, 发送消息
        AddMessageReq messageReq = new AddMessageReq ();
        messageReq.setMessageType (MsgTypeEnums.REPLY.getType ());
        messageReq.setSourceId (req.getRootId ());
        messageReq.setReceiveUseId (commentFeignClient.getRootComment (req.getRootId ()).getResult ().getUserId ());
        msgService.addMessage (messageReq);
        
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delRootComment(Long rootCommentId) {
        // 参数校验 (id 是否存在)
        if (!commentFeignClient.verifyRootComment (rootCommentId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 删除父评论
        CommonDtoResult<Boolean> dtoResult = commentFeignClient.delRootComment (rootCommentId).getResult ();
    
        // 删除失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        // 删除成功
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delSonComment(Long sunCommentId) {
        // 参数校验 (id 是否存在)
        if (!commentFeignClient.verifySonComment (sunCommentId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 删除子评论
        CommonDtoResult<Boolean> dtoResult = commentFeignClient.delSonComment (sunCommentId).getResult ();
    
        // 删除失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        // 删除成功
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> likeRootComment(LikeReq req) {
        // 参数校验 (id)
        if (!commentFeignClient.verifyRootComment (req.getSourceId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 获取点赞人用户 ID
        Long userId = userService.getUser ().getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (ROOT_COMMENT_LIKE_KEY, req.getSourceId (), userId);
        
        if (!req.getLike ()) {
            // 取消点赞
            // 本身未点赞
            if (!isLike) {
                throw new BizException ("您已经取消点赞了");
            }
            // 更新 redis
            Integer likeCount = likeService.unLike (ROOT_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = commentFeignClient.likeRootComment (dtoReq).getResult ();
            
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
            Integer likeCount = likeService.like (ROOT_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = commentFeignClient.likeRootComment (dtoReq).getResult ();
            
            if (!dtoResult.getData ()) {
                throw new BizException (dtoResult.getMessage ());
            }
            // 点赞成功, 发送消息
            RootCommentDetailDtoResult rootComment = commentFeignClient
                    .getRootComment (req.getSourceId ()).getResult ();
            if (AnswerTypeEnums.NOTE.getType ().equals (rootComment.getAnswerType ())) {
                AddMessageReq messageReq = new AddMessageReq ();
                messageReq.setReceiveUseId (noteFeignClient.noteDetail (rootComment.getAnswerId ())
                        .getResult ().getUserId ());
                messageReq.setMessageType (MsgTypeEnums.LIKE.getType ());
                messageReq.setSourceId (req.getSourceId ());
                msgService.addMessage (messageReq);
            }
            return CommonVO.create (dtoResult);
        }
    }
    
    @Override
    public CommonVO<Boolean> likeSonComment(LikeReq req) {
        // 参数校验 (id)
        if (!commentFeignClient.verifySonComment (req.getSourceId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 获取点赞人用户 ID
        Long userId = userService.getUser ().getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (SON_COMMENT_LIKE_KEY, req.getSourceId (), userId);
    
        if (!req.getLike ()) {
            // 取消点赞
            // 本身未点赞
            if (!isLike) {
                throw new BizException ("您已经取消点赞了");
            }
            // 更新 redis
            Integer likeCount = likeService.unLike (SON_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = commentFeignClient.likeSonComment (dtoReq).getResult ();
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
            Integer likeCount = likeService.like (SON_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = commentFeignClient.likeSonComment (dtoReq).getResult ();
            
            if (!dtoResult.getData ()) {
                throw new BizException (dtoResult.getMessage ());
            }
            // 点赞成功, 发送消息
            AddMessageReq messageReq = new AddMessageReq ();
            messageReq.setMessageType (MsgTypeEnums.LIKE.getType ());
            messageReq.setSourceId (req.getSourceId ());
            messageReq.setReceiveUseId (commentFeignClient.getSonComment (req.getSourceId ()).getResult ().getUserId ());
            msgService.addMessage (messageReq);
            return CommonVO.create (dtoResult);
        }
    }
    
    @Override
    public PageVO<RootCommentDetailVO> rootCommentDetail(RootCommentDetailReq req) {
        // 评论内容参数校验
        if (AnswerTypeEnums.NOTE.getType ().equals (req.getAnswerType ()) &&
                !noteFeignClient.verifyNote (req.getAnswerId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (AnswerTypeEnums.QUESTION.getType ().equals (req.getAnswerType ()) &&
                !questionFeignClient.verifyQuestion (req.getAnswerId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        RootCommentDetailDtoReq dtoReq = BeanUtil.toBean (req, RootCommentDetailDtoReq.class);
        
        // 获取所有评论
        PageDtoResult<RootCommentDetailDtoResult> dtoResult = commentFeignClient.rootCommentDetail (dtoReq).getResult ();
    
        // 转为 PageVO 格式
        PageVO<RootCommentDetailVO> pageVO = PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, RootCommentDetailVO.class)
                
        );
        
        
        if (CollectionUtil.isEmpty (pageVO.getRecords ())) {
            return pageVO;
        }
        
        // 获取用户通用信息
        List<Long> userIdList = pageVO.getRecords ().stream ()
                .map (RootCommentDetailVO::getUserId)
                .collect (Collectors.toList ());
        userIdList.addAll (pageVO.getRecords ().stream ()
                .map (RootCommentDetailVO::getSonCommentDetailList)
                .flatMap ((o) -> o.stream ()
                        .flatMap ((item) -> Stream.of (item.getUserId (), item.getReplyUserId ())))
                .collect(Collectors.toList()));
        Map<Long, UserCommonDtoResult> commonInfoMap = userService.getUserCommonInfo (userIdList).stream ()
                .collect (Collectors.toMap (
                        UserCommonDtoResult::getUserId,
                        Function.identity (),
                        (k1, k2) -> k1));
    
        // 设置用户名, 头像等信息
        pageVO.getRecords ().forEach ((root) -> {
            UserCommonDtoResult rootUser = commonInfoMap.get (root.getUserId ());
            root.setUserName (rootUser.getUserName ());
            root.setUserAvatar (rootUser.getUserAvatar ());
            root.getSonCommentDetailList ().forEach ((son) -> {
                UserCommonDtoResult sunCommentUser = commonInfoMap.get (son.getUserId ());
                son.setUserName (sunCommentUser.getUserName ());
                UserCommonDtoResult sunReplyUser = commonInfoMap.get (son.getReplyUserId ());
                son.setReplyUserName (sunReplyUser.getUserName ());
            });
        });
        return pageVO;
    }
    
    @Override
    public PageVO<SonCommentDetailVO> sonCommentDetail(SonCommentDetailReq req) {
        // 参数校验 (rootId)
        if (!commentFeignClient.verifyRootComment (req.getRootId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        SonCommentDetailDtoReq dtoReq = BeanUtil.toBean (req, SonCommentDetailDtoReq.class);
    
        // 获取所有评论
        PageDtoResult<SonCommentDetailDtoResult> dtoResult = commentFeignClient.sonCommentDetail (dtoReq).getResult ();
    
        PageVO<SonCommentDetailVO> pageVO = PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, SonCommentDetailVO.class)
        );
    
        if (CollectionUtil.isEmpty (pageVO.getRecords ())) {
            return pageVO;
        }
    
        // 获取用户通用信息
        List<Long> userIdList = pageVO.getRecords ().stream ()
                .flatMap (((item) -> Stream.of (item.getReplyUserId (), item.getReplyUserId ())))
                .collect (Collectors.toList ());
        Map<Long, UserCommonDtoResult> commonInfoMap = userService.getUserCommonInfo (userIdList).stream ()
                .collect (Collectors.toMap (
                        UserCommonDtoResult::getUserId,
                        Function.identity (),
                        (k1, k2) -> k1));
        
        // 设置用户名, 头像等信息
        pageVO.getRecords ().forEach ((son) -> {
            UserCommonDtoResult sunCommentUser = commonInfoMap.get (son.getUserId ());
            son.setUserName (sunCommentUser.getUserName ());
            UserCommonDtoResult sunReplyUser = commonInfoMap.get (son.getReplyUserId ());
            son.setReplyUserName (sunReplyUser.getUserName ());
        });
        return pageVO;
    }
}
