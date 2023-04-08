package com.yixihan.yicode.question.openapi.biz.service.note.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.enums.question.AnswerTypeEnums;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.AddMessageReq;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.ModifyNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.QueryNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
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
import com.yixihan.yicode.user.api.dto.request.extra.VerifyFavoriteTypeDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.NOTE_LIKE_KEY;

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
    
    @Override
    public NoteVO addNote(ModifyNoteReq req) {
        // 校验参数 (问题 ID, 题解标题)
        Assert.isFalse (StrUtil.isBlank (req.getNoteName ()));
        // 校验问题 ID 是否存在
        Assert.isTrue (questionFeignClient.verifyQuestion (req.getQuestionId ()).getResult ());
        
        // 构建请求 body
        ModifyNoteDtoReq dtoReq = BeanUtil.toBean (req, ModifyNoteDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());

        // 添加题解
        NoteDtoResult dtoResult = noteFeignClient.addNote (dtoReq).getResult ();

        return setUserCommonInfo(dtoResult);
    }
    
    @Override
    public NoteVO modifyNote(ModifyNoteReq req) {
        // 校验参数 (题解 ID, 题解标题)
        Assert.isFalse (StrUtil.isBlank (req.getNoteName ()));
        // 校验问题 ID 是否存在
        Assert.notNull (req.getNoteId ());
        Assert.isTrue (noteFeignClient.verifyNote (req.getNoteId ()).getResult ());
    
        // 构建请求 body
        ModifyNoteDtoReq dtoReq = BeanUtil.toBean (req, ModifyNoteDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
    
        // 修改题解
        NoteDtoResult dtoResult = noteFeignClient.modifyNote (dtoReq).getResult ();

        return setUserCommonInfo(dtoResult);
    }
    
    @Override
    public void delNote(List<Long> noteIdList) {
        // 校验参数 (参数不为空)
        Assert.isFalse (CollUtil.isEmpty (noteIdList));
    
        // 删除题解
        noteFeignClient.delNote (noteIdList);
    }
    
    @Override
    public void likeNote(LikeReq req) {
        // 参数校验 (id)
        Assert.isTrue (noteFeignClient.verifyNote (req.getSourceId ()).getResult ());
        
        // 获取点赞人用户 ID
        Long userId = userService.getUserId ();
        // 获取点赞情况
        Boolean isLike = likeService.isLike (NOTE_LIKE_KEY,req.getSourceId (), userId);
    
        if (Boolean.FALSE.equals (req.getLike ())) {
            // 取消点赞
            // 本身未点赞
            Assert.isTrue (isLike, new BizException ("您已经取消点赞了"));

            // 更新 redis
            Integer likeCount = likeService.unLike (NOTE_LIKE_KEY, req.getSourceId (), userId);
            
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            noteFeignClient.likeNote (dtoReq);
        } else {
            // 点赞
            // 本身已点赞
            Assert.isFalse (isLike, new BizException ("您已经点赞了"));
            
            // 更新 redis
            Integer likeCount = likeService.like (NOTE_LIKE_KEY, req.getSourceId (), userId);
            // 更新数据库
            LikeDtoReq dtoReq = new LikeDtoReq (userId, req.getSourceId (), likeCount);
            noteFeignClient.likeNote (dtoReq);
            
            // 点赞成功, 发送消息
            NoteVO noteVO = noteDetail (dtoReq.getSourceId ());
            sendMessage (MsgTypeEnums.LIKE, req.getSourceId (), noteVO.getUserId ());
        }
    }
    
    
    @Override
    public void collectionNote(ModifyCollectionReq req) {
        // 校验收藏夹类型
        VerifyFavoriteTypeDtoReq verifyFavoriteTypeDtoReq = new VerifyFavoriteTypeDtoReq (
                req.getFavoriteId (),
                AnswerTypeEnums.NOTE.getType ()
        );
        Assert.isTrue (favoriteFeignClient.verifyFavoriteType (verifyFavoriteTypeDtoReq).getResult ());
        // 校验收藏内容 ID
        Assert.isTrue (noteFeignClient.verifyNote (req.getCollectionId ()).getResult ());
    
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
        
        // 收藏内容
        collectionFeignClient.addCollection (dtoReq);
    
        // 收藏成功, 发送消息
        NoteVO noteVO = noteDetail (dtoReq.getCollectionId ());
        sendMessage (MsgTypeEnums.COLLECTION, req.getCollectionId (), noteVO.getUserId ());
    }
    
    @Override
    public void cancelCollectionNote(ModifyCollectionReq req) {
        // 校验收藏夹类型
        VerifyFavoriteTypeDtoReq verifyFavoriteTypeDtoReq = new VerifyFavoriteTypeDtoReq (
                req.getFavoriteId (),
                AnswerTypeEnums.NOTE.getType ()
        );
        Assert.isTrue (favoriteFeignClient.verifyFavoriteType (verifyFavoriteTypeDtoReq).getResult ());
        // 校验收藏内容 ID
        Assert.isTrue (noteFeignClient.verifyNote (req.getCollectionId ()).getResult ());
        
        // 构造请求 body
        ModifyCollectionDtoReq dtoReq = BeanUtil.toBean (req, ModifyCollectionDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
    
        // 取消收藏内容
        collectionFeignClient.delCollection (dtoReq);
    }
    
    @Override
    public List<LabelVO> modifyNoteLabel(ModifyLabelNoteReq req) {
        // 校验参数
        Assert.isTrue (noteFeignClient.verifyNote (req.getNoteId ()).getResult ());
        req.getLabelIdList ().forEach (labelId ->
                Assert.isTrue (labelFeignClient.verifyLabel (labelId).getResult ()));
        
        // 构造请求 body
        ModifyLabelNoteDtoReq dtoReq = BeanUtil.toBean (req, ModifyLabelNoteDtoReq.class);
        
        // 添加题解标签
        List<LabelDtoResult> dtoResult = labelNoteFeignClient.modifyNoteLabel (dtoReq).getResult ();

        return BeanUtil.copyToList (dtoResult, LabelVO.class);
    }
    
    @Override
    public NoteVO noteDetail(Long noteId) {
        // 校验参数 (题解 ID)
        Assert.notNull (noteId);
        Assert.isTrue (noteFeignClient.verifyNote (noteId).getResult ());
    
        // 获取题解明细
        NoteDtoResult dtoResult = noteFeignClient.noteDetail (noteId).getResult ();
        
        return setUserCommonInfo(dtoResult);
    }
    
    @Override
    public PageVO<NoteVO> queryNote(QueryNoteReq req) {
        // 构建请求 body
        QueryNoteDtoReq dtoReq = BeanUtil.toBean (req, QueryNoteDtoReq.class);
        
        // 搜索题解
        PageDtoResult<NoteDtoResult> dtoResult = noteFeignClient.queryNote (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (dtoResult, this::setUserCommonInfo);
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

    private NoteVO setUserCommonInfo (NoteDtoResult dtoResult) {
        UserCommonDtoResult commonInfo = userService.getUserCommonInfo(CollUtil.newArrayList(dtoResult.getUserId())).get(0);
        NoteVO vo = BeanUtil.toBean(dtoResult, NoteVO.class);

        vo.setUserName(commonInfo.getUserName());
        vo.setUserAvatar(commonInfo.getUserAvatar());

        return vo;
    }
}
