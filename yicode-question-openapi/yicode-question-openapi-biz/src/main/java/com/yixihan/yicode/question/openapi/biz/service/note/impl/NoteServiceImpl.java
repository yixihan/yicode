package com.yixihan.yicode.question.openapi.biz.service.note.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.AddMessageReq;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.ModifyNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.QueryNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.response.note.NoteVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelNoteFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.note.NoteFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserCollectionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserFavoriteFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.LikeService;
import com.yixihan.yicode.question.openapi.biz.service.message.UserMsgService;
import com.yixihan.yicode.question.openapi.biz.service.note.NoteService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题解 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:22
 */
@Slf4j
@Service
public class NoteServiceImpl implements NoteService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private UserMsgService msgService;
    
    @Resource
    private NoteFeignClient noteFeignClient;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Resource
    private UserCollectionFeignClient collectionFeignClient;
    
    @Resource
    private UserFavoriteFeignClient favoriteFeignClient;
    
    @Resource
    private LabelNoteFeignClient labelNoteFeignClient;
    
    @Resource
    private LabelFeignClient labelFeignClient;
    
    @Resource
    private LikeService likeService;
    
    /**
     * redis key : 点赞题解
     */
    private static final String NOTE_LIKE_KEY = "like:note";
    
    @Override
    public CommonVO<Boolean> addNote(ModifyNoteReq req) {
        // 校验参数 (问题 ID, 题解标题)
        if (StrUtil.isBlank (req.getNoteName ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 校验问题 ID 是否存在
        if (!questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构建请求 body
        ModifyNoteDtoReq dtoReq = CopyUtils.copySingle (ModifyNoteDtoReq.class, req);
        dtoReq.setUserId (userService.getUser ().getUserId ());

        // 添加题解
        CommonDtoResult<Boolean> dtoResult = noteFeignClient.addNote (dtoReq).getResult ();
        
        // 如果添加失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        
        // 添加成功
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> modifyNote(ModifyNoteReq req) {
        // 校验参数 (题解 ID, 题解标题)
        if (StrUtil.isBlank (req.getNoteName ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (req.getNoteId () == null || !noteFeignClient.verifyNote (req.getNoteId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构建请求 body
        ModifyNoteDtoReq dtoReq = CopyUtils.copySingle (ModifyNoteDtoReq.class, req);
        dtoReq.setUserId (userService.getUser ().getUserId ());
    
        // 修改题解
        CommonDtoResult<Boolean> dtoResult = noteFeignClient.modifyNote (dtoReq).getResult ();
    
        // 如果修改失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        // 修改成功
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delNote(List<Long> noteIdList) {
        // 校验参数 (参数不为空)
        if (CollectionUtil.isEmpty (noteIdList)) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 删除题解
        CommonDtoResult<Boolean> dtoResult = noteFeignClient.delNote (noteIdList).getResult ();
        
        // 如果删除失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
    
        // 删除成功
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> likeNote(LikeReq req) {
        // 参数校验 (id)
        if (!noteFeignClient.verifyNote (req.getSourceId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 获取点赞人用户 ID
        Long userId = userService.getUser ().getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (NOTE_LIKE_KEY,req.getSourceId (), userId);
    
        if (!req.getLike ()) {
            // 取消点赞
            // 本身未点赞
            if (isLike == null || !isLike) {
                throw new BizException ("您已经取消点赞了");
            }
            // 更新 redis
            Integer likeCount = likeService.unLike (NOTE_LIKE_KEY, req.getSourceId (), userId);
            
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = noteFeignClient.likeNote (dtoReq).getResult ();
            
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
            Integer likeCount = likeService.like (NOTE_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            CommonDtoResult<Boolean> dtoResult = noteFeignClient.likeNote (dtoReq).getResult ();
            
            if (!dtoResult.getData ()) {
                throw new BizException (dtoResult.getMessage ());
            }
            
            // 点赞成功, 发送消息
            AddMessageReq messageReq = new AddMessageReq ();
            messageReq.setMessageType (MsgTypeEnums.LIKE.getType ());
            messageReq.setSourceId (req.getSourceId ());
            NoteVO noteVO = noteDetail (dtoReq.getSourceId ());
            messageReq.setReceiveUseId (noteVO.getUserId ());
            msgService.addMessage (messageReq);
            return CommonVO.create (dtoResult);
        }
    }
    
    
    @Override
    public CommonVO<Boolean> collectionNote(ModifyCollectionReq req) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹 ID
        if (!favoriteFeignClient.verifyFavorite (req.getFavoriteId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 校验收藏内容 ID
        if (!noteFeignClient.verifyNote (req.getCollectionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = CopyUtils.copySingle (ModifyCollectionDtoReq.class, req);
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
    public CommonVO<Boolean> cancelCollectionNote(ModifyCollectionReq req) {
        Long userId = userService.getUser ().getUserId ();
        // 校验收藏夹 ID
        if (!favoriteFeignClient.verifyFavorite (req.getFavoriteId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        // 校验收藏内容 ID
        if (!noteFeignClient.verifyNote (req.getCollectionId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = CopyUtils.copySingle (ModifyCollectionDtoReq.class, req);
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
    public CommonVO<Boolean> addNoteLabel(ModifyLabelNoteReq req) {
        // 校验参数
        if (!labelFeignClient.verifyLabel (req.getLabelId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (!noteFeignClient.verifyNote (req.getNoteId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构造请求 body
        ModifyLabelNoteDtoReq dtoReq = CopyUtils.copySingle (ModifyLabelNoteDtoReq.class, req);
        
        // 添加题解标签
        CommonDtoResult<Boolean> dtoResult = labelNoteFeignClient.addNoteLabel (dtoReq).getResult ();
    
        // 如果操作失败, 排除异常原因
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delNoteLabel(ModifyLabelNoteReq req) {
        // 校验参数
        if (!labelFeignClient.verifyLabel (req.getLabelId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        if (!noteFeignClient.verifyNote (req.getNoteId ()).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 构造请求 body
        ModifyLabelNoteDtoReq dtoReq = CopyUtils.copySingle (ModifyLabelNoteDtoReq.class, req);
    
        // 添加题解标签
        CommonDtoResult<Boolean> dtoResult = labelNoteFeignClient.delNoteLabel (dtoReq).getResult ();
    
        // 如果操作失败, 排除异常原因
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public NoteVO noteDetail(Long noteId) {
        // 校验参数 (题解 ID)
        if (noteId == null || !noteFeignClient.verifyNote (noteId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 获取题解明细
        NoteDtoResult dtoResult = noteFeignClient.noteDetail (noteId).getResult ();
        
        return CopyUtils.copySingle (NoteVO.class, dtoResult);
    }
    
    @Override
    public PageVO<NoteVO> queryNote(QueryNoteReq req) {
        // 构建请求 body
        QueryNoteDtoReq dtoReq = CopyUtils.copySingle (QueryNoteDtoReq.class, req);
        
        // 搜索题解
        PageDtoResult<NoteDtoResult> dtoResult = noteFeignClient.queryNote (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> CopyUtils.copySingle (NoteVO.class, o)
        );
    }
}
