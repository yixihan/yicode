package com.yixihan.yicode.question.openapi.biz.service.comment.impl;

import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.comment.AddRootCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddSonCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.RootCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.SonCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.openapi.api.enums.AnswerTypeEnums;
import com.yixihan.yicode.question.openapi.api.vo.request.AddMessageReq;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddRootCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddSonCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.RootCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.SonCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.RootCommentDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.SonCommentDetailVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.comment.CommentFeignClient;
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
    private UserMsgService msgService;
    
    @Override
    public CommonVO<Boolean> addRootComment(AddRootCommentReq req) {
        // 参数校验 (评论内容不能为空)
        if (StrUtil.isBlank (req.getContent ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        AddRootCommentDtoReq dtoReq = CopyUtils.copySingle (AddRootCommentDtoReq.class, req);
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
            // TODO 获取接收者用户 ID
            messageReq.setReceiveUseId (null);
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
        AddSonCommentDtoReq dtoReq = CopyUtils.copySingle (AddSonCommentDtoReq.class, req);
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
        messageReq.setReceiveUseId (commentFeignClient.getRootComment (req.getRootId ()).getResult ().getRootId ());
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
        CommonDtoResult<Boolean> dtoResult = commentFeignClient.delRootComment (sunCommentId).getResult ();
    
        // 删除失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        // 删除成功
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> likeRootComment(LikeReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> likeSonComment(LikeReq req) {
        return null;
    }
    
    @Override
    public PageVO<RootCommentDetailVO> rootCommentDetail(RootCommentDetailReq req) {
        // 构建请求 body
        RootCommentDetailDtoReq dtoReq = CopyUtils.copySingle (RootCommentDetailDtoReq.class, req);
        
        // 获取所有评论
        PageDtoResult<RootCommentDetailDtoResult> dtoResult = commentFeignClient.rootCommentDetail (dtoReq).getResult ();
    
        // 转为 PageVO 格式
        PageVO<RootCommentDetailVO> pageVO = PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> CopyUtils.copySingle (RootCommentDetailVO.class, o)
        );
        
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
                UserCommonDtoResult sunUser = commonInfoMap.get (root.getUserId ());
                root.setUserName (sunUser.getUserName ());
            });
        });
        return pageVO;
    }
    
    @Override
    public PageVO<SonCommentDetailVO> sonCommentDetail(SonCommentDetailReq req) {
        // 构建请求 body
        SonCommentDetailDtoReq dtoReq = CopyUtils.copySingle (SonCommentDetailDtoReq.class, req);
    
        // 获取所有评论
        PageDtoResult<SonCommentDetailDtoResult> dtoResult = commentFeignClient.sonCommentDetail (dtoReq).getResult ();
    
        PageVO<SonCommentDetailVO> pageVO = PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> CopyUtils.copySingle (SonCommentDetailVO.class, o)
        );
    
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
            UserCommonDtoResult sonUser = commonInfoMap.get (son.getUserId ());
            son.setUserName (sonUser.getUserName ());
        });
        return pageVO;
    }
}
