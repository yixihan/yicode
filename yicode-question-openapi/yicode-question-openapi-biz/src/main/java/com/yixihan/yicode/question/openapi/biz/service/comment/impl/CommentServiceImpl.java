package com.yixihan.yicode.question.openapi.biz.service.comment.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.enums.question.AnswerTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddRootCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddSonCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.RootCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.SonCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
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
import org.springframework.scheduling.annotation.Async;
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
    public RootCommentDetailVO addRootComment(AddRootCommentReq req) {
        // 参数校验 (评论, 评论内容 ID)
        Assert.isFalse (StrUtil.isBlank (req.getContent ()));
        Assert.isFalse (AnswerTypeEnums.NOTE.getType ().equals (req.getAnswerType ()) &&
                Boolean.FALSE.equals(noteFeignClient.verifyNote (req.getAnswerId ()).getResult ()));
        Assert.isFalse (AnswerTypeEnums.QUESTION.getType ().equals (req.getAnswerType ()) &&
                Boolean.FALSE.equals(questionFeignClient.verifyQuestion (req.getAnswerId ()).getResult ()));
        
        // 构建请求 body
        AddRootCommentDtoReq dtoReq = BeanUtil.toBean (req, AddRootCommentDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 添加父评论
        RootCommentDetailDtoResult dtoResult = commentFeignClient.addRootComment (dtoReq).getResult ();
        
        if (AnswerTypeEnums.NOTE.getType ().equals (req.getAnswerType ())) {
            // 发送消息
            sendMessage (MsgTypeEnums.REPLY, req.getAnswerId (),
                    noteFeignClient.noteDetail (req.getAnswerId ()).getResult ().getUserId ());
        }
        return BeanUtil.toBean (dtoResult, RootCommentDetailVO.class);
    }
    
    @Override
    public SonCommentDetailVO addSonComment(AddSonCommentReq req) {
        // 参数校验 (id 是否存在 & 评论内容不能为空)
        Assert.isFalse (StrUtil.isBlank (req.getContent ()));
        Assert.isTrue (commentFeignClient.verifyRootComment (req.getRootId ()).getResult ());
        if (StrUtil.isBlank (req.getContent ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (Boolean.FALSE.equals (commentFeignClient.verifyRootComment (req.getRootId ()).getResult ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构建请求 body
        AddSonCommentDtoReq dtoReq = BeanUtil.toBean (req, AddSonCommentDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 添加子评论
        SonCommentDetailDtoResult dtoResult = commentFeignClient.addSonComment (dtoReq).getResult ();
        
        // 添加成功, 发送消息
        sendMessage (MsgTypeEnums.REPLY, req.getRootId (), commentFeignClient.getRootComment (req.getRootId ()).getResult ().getUserId ());
    
        return BeanUtil.toBean (dtoResult, SonCommentDetailVO.class);
    }
    
    @Override
    public void delRootComment(Long rootCommentId) {
        // 参数校验 (id 是否存在)
        Assert.isTrue (commentFeignClient.verifyRootComment (rootCommentId).getResult ());
    
        // 删除父评论
        commentFeignClient.delRootComment (rootCommentId);
    }
    
    @Override
    public void delSonComment(Long sunCommentId) {
        // 参数校验 (id 是否存在)
        Assert.isTrue (commentFeignClient.verifySonComment (sunCommentId).getResult ());
        
        // 删除子评论
        commentFeignClient.delSonComment (sunCommentId);
    }
    
    @Override
    public void likeRootComment(LikeReq req) {
        // 参数校验 (id)
        Assert.isTrue (commentFeignClient.verifyRootComment (req.getSourceId ()).getResult ());
        
        // 获取点赞人用户 ID
        Long userId = userService.getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (ROOT_COMMENT_LIKE_KEY, req.getSourceId (), userId);
        
        if (Boolean.FALSE.equals (req.getLike ())) {
            // 取消点赞
            // 本身未点赞
            Assert.isTrue (isLike, new BizException ("您已经取消点赞了"));
            
            // 更新 redis
            Integer likeCount = likeService.unLike (ROOT_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            commentFeignClient.likeRootComment (dtoReq);
        } else {
            // 点赞
            // 本身已点赞
            Assert.isFalse (isLike, new BizException ("您已经点赞了"));
            
            // 更新 redis
            Integer likeCount = likeService.like (ROOT_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            commentFeignClient.likeRootComment (dtoReq);

            // 点赞成功, 发送消息
            RootCommentDetailDtoResult rootComment = commentFeignClient
                    .getRootComment (req.getSourceId ()).getResult ();
            if (AnswerTypeEnums.NOTE.getType ().equals (rootComment.getAnswerType ())) {
                sendMessage (MsgTypeEnums.LIKE, req.getSourceId (),
                        noteFeignClient.noteDetail (rootComment.getAnswerId ()).getResult ().getUserId ());
            }
        }
    }
    
    @Override
    public void likeSonComment(LikeReq req) {
        // 参数校验 (id)
        Assert.isTrue (commentFeignClient.verifySonComment (req.getSourceId ()).getResult ());

        // 获取点赞人用户 ID
        Long userId = userService.getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (SON_COMMENT_LIKE_KEY, req.getSourceId (), userId);
    
        if (Boolean.FALSE.equals (req.getLike ())) {
            // 取消点赞
            // 本身未点赞
            Assert.isTrue (isLike, new BizException ("您已经取消点赞了"));
            
            // 更新 redis
            Integer likeCount = likeService.unLike (SON_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            commentFeignClient.likeSonComment (dtoReq);
        } else {
            // 点赞
            // 本身已点赞
            Assert.isFalse (isLike, new BizException ("您已经点赞了"));
            
            // 更新 redis
            Integer likeCount = likeService.like (SON_COMMENT_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            commentFeignClient.likeSonComment (dtoReq);
            
            // 点赞成功, 发送消息
            sendMessage (MsgTypeEnums.LIKE, req.getSourceId (),
                    commentFeignClient.getSonComment (req.getSourceId ()).getResult ().getUserId ());
        }
    }
    
    @Override
    public PageVO<RootCommentDetailVO> rootCommentDetail(RootCommentDetailReq req) {
        // 评论内容参数校验
        Assert.isFalse (AnswerTypeEnums.NOTE.getType ().equals (req.getAnswerType ()) &&
                Boolean.FALSE.equals(noteFeignClient.verifyNote (req.getAnswerId ()).getResult ()));
        Assert.isFalse (AnswerTypeEnums.QUESTION.getType ().equals (req.getAnswerType ()) &&
                Boolean.FALSE.equals(questionFeignClient.verifyQuestion (req.getAnswerId ()).getResult ()));
        
        // 构建请求 body
        RootCommentDetailDtoReq dtoReq = BeanUtil.toBean (req, RootCommentDetailDtoReq.class);
        
        // 获取所有评论
        PageDtoResult<RootCommentDetailDtoResult> dtoResult = commentFeignClient.rootCommentDetail (dtoReq).getResult ();
    
        // 转为 PageVO 格式
        PageVO<RootCommentDetailVO> pageVO = PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, RootCommentDetailVO.class)
                
        );
        
        if (CollUtil.isEmpty (pageVO.getRecords ())) {
            return pageVO;
        }
        
        // 获取用户通用信息
        List<Long> userIdList = pageVO.getRecords ().stream ()
                .map (RootCommentDetailVO::getUserId)
                .collect (Collectors.toList ());
        userIdList.addAll (pageVO.getRecords ().stream ()
                .map (RootCommentDetailVO::getSonCommentDetailList)
                .flatMap (o -> {
                    if (CollUtil.isNotEmpty (o)) {
                        return o.stream ()
                                .flatMap (item -> Stream.of (item.getUserId (), item.getReplyUserId ()));
                    }
                    return null;
                })
                .collect(Collectors.toList()));
        Map<Long, UserCommonDtoResult> commonInfoMap = userService.getUserCommonInfo (userIdList).stream ()
                .collect (Collectors.toMap (
                        UserCommonDtoResult::getUserId,
                        Function.identity (),
                        (k1, k2) -> k1));
    
        // 设置用户名, 头像等信息
        pageVO.getRecords ().parallelStream ().forEach (root -> {
            UserCommonDtoResult rootUser = commonInfoMap.get (root.getUserId ());
            root.setUserName (rootUser.getUserName ());
            root.setUserAvatar (rootUser.getUserAvatar ());
            if (CollUtil.isNotEmpty (root.getSonCommentDetailList ())) {
                root.getSonCommentDetailList ().parallelStream ().forEach (son -> {
                    UserCommonDtoResult sunCommentUser = commonInfoMap.get (son.getUserId ());
                    son.setUserName (sunCommentUser.getUserName ());
                    UserCommonDtoResult sunReplyUser = commonInfoMap.get (son.getReplyUserId ());
                    son.setReplyUserName (sunReplyUser.getUserName ());
                });
            }
        });
        return pageVO;
    }
    
    @Override
    public PageVO<SonCommentDetailVO> sonCommentDetail(SonCommentDetailReq req) {
        // 参数校验 (rootId)
        Assert.isTrue (commentFeignClient.verifyRootComment (req.getRootId ()).getResult ());
        
        // 构建请求 body
        SonCommentDetailDtoReq dtoReq = BeanUtil.toBean (req, SonCommentDetailDtoReq.class);
    
        // 获取所有评论
        PageDtoResult<SonCommentDetailDtoResult> dtoResult = commentFeignClient.sonCommentDetail (dtoReq).getResult ();
    
        PageVO<SonCommentDetailVO> pageVO = PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, SonCommentDetailVO.class)
        );
    
        if (CollUtil.isEmpty (pageVO.getRecords ())) {
            return pageVO;
        }
    
        // 获取用户通用信息
        List<Long> userIdList = pageVO.getRecords ().stream ()
                .flatMap ((item -> Stream.of (item.getReplyUserId (), item.getReplyUserId ())))
                .collect (Collectors.toList ());
        Map<Long, UserCommonDtoResult> commonInfoMap = userService.getUserCommonInfo (userIdList).stream ()
                .collect (Collectors.toMap (
                        UserCommonDtoResult::getUserId,
                        Function.identity (),
                        (k1, k2) -> k1));
        
        // 设置用户名, 头像等信息
        pageVO.getRecords ().parallelStream ().forEach (son -> {
            UserCommonDtoResult sunCommentUser = commonInfoMap.get (son.getUserId ());
            son.setUserName (sunCommentUser.getUserName ());
            UserCommonDtoResult sunReplyUser = commonInfoMap.get (son.getReplyUserId ());
            son.setReplyUserName (sunReplyUser.getUserName ());
        });
        return pageVO;
    }
    
    /**
     * 通用-发送信息
     *
     * @param type 消息类型
     * @param sourceId 发送消息对应 id
     * @param userId 接收者用户 id
     */
    @Async
    public void sendMessage(MsgTypeEnums type, Long sourceId, Long userId) {
        AddMessageReq messageReq = new AddMessageReq ();
        messageReq.setMessageType (type.getType ());
        messageReq.setSourceId (sourceId);
        messageReq.setReceiveUseId (userId);
        msgService.addMessage (messageReq);
    }
}
