package com.yixihan.yicode.question.biz.service.comment.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.enums.question.AnswerTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.*;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.biz.service.comment.CommentReplyService;
import com.yixihan.yicode.question.biz.service.comment.CommentRootService;
import com.yixihan.yicode.question.biz.service.note.NoteService;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import com.yixihan.yicode.question.dal.mapper.comment.CommentRootMapper;
import com.yixihan.yicode.question.dal.pojo.comment.CommentReply;
import com.yixihan.yicode.question.dal.pojo.comment.CommentRoot;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 父评论表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class CommentRootServiceImpl extends ServiceImpl<CommentRootMapper, CommentRoot> implements CommentRootService {
    
    @Resource
    private CommentReplyService commentReplyService;
    
    @Resource
    private QuestionService questionService;
    
    @Resource
    private NoteService noteService;
    
    @Override
    public RootCommentDetailDtoResult addRootComment(AddRootCommentDtoReq dtoReq) {
        CommentRoot commentRoot = BeanUtil.toBean (dtoReq, CommentRoot.class);
    
        // 保存
        Assert.isTrue (save (commentRoot), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        // 异步更新评论内容评论数
        modifyAnswerCommentCount (dtoReq.getAnswerId (), dtoReq.getAnswerType ());
        
        return BeanUtil.toBean (commentRoot, RootCommentDetailDtoResult.class);
    }
    
    @Override
    public SonCommentDetailDtoResult addSonComment(AddSonCommentDtoReq dtoReq) {
        CommentReply commentReply = BeanUtil.toBean (dtoReq, CommentReply.class);
    
        // 保存
        Assert.isTrue (commentReplyService.save (commentReply), BizCodeEnum.FAILED_TYPE_BUSINESS);

        // 更新父评论回复数量
        Long rootId = commentReply.getRootId ();
        modifyRootCommentReply (rootId);
        // 异步更新评论内容评论数
        CommentRoot commentRoot = baseMapper.selectById (rootId);
        modifyAnswerCommentCount (commentRoot.getAnswerId (), commentRoot.getAnswerType ());
    
        return BeanUtil.toBean (commentReply, SonCommentDetailDtoResult.class);
    }
    
    @Override
    public void delRootComment(Long rootCommentId) {
        // 异步更新评论内容评论数
        CommentRoot commentRoot = baseMapper.selectById (rootCommentId);
    
        // 删除父评论
        Assert.isTrue (removeById (rootCommentId), BizCodeEnum.FAILED_TYPE_BUSINESS);
        // 获取子评论 id
        List<Long> sonCommentIdList = sonCommentDetail (CollUtil.newArrayList (rootCommentId))
                .values ()
                .stream()
                .findFirst ()
                .orElse (Collections.emptyList ())
                .stream ()
                .map (SonCommentDetailDtoResult::getReplyId)
                .collect(Collectors.toList());
        // 删除子评论
        Assert.isTrue (commentReplyService.removeByIds (sonCommentIdList), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
    
        // 异步更新评论内容评论数
        modifyAnswerCommentCount (commentRoot.getAnswerId (), commentRoot.getAnswerType ());
    }
    
    @Override
    public void delSonComment(Long sunCommentId) {
        CommentReply commentReply = commentReplyService.getById (sunCommentId);
        
        // 删除
        Assert.isTrue (commentReplyService.removeById (sunCommentId), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        // 更新父评论回复数量
        Long rootId = commentReply.getRootId ();
        modifyRootCommentReply (rootId);
        // 异步更新评论内容评论数
        CommentRoot commentRoot = baseMapper.selectById (rootId);
        modifyAnswerCommentCount (commentRoot.getAnswerId (), commentRoot.getAnswerType ());
    }
    
    @Override
    public Integer commentCount(QuestionCommentCountDtoReq dtoReq) {
        // 获取父评论 id 列表
        List<Long > commentRootIdList = lambdaQuery ()
                .eq (CommentRoot::getAnswerId, dtoReq.getAnswerId ())
                .eq (CommentRoot::getAnswerType, dtoReq.getAnswerType ())
                .list ()
                .stream ()
                .map (CommentRoot::getRootId)
                .collect(Collectors.toList());
    
        if (CollectionUtil.isEmpty (commentRootIdList)) {
            return NumConstant.NUM_0;
        }
    
        // 获取子评论数量
        Integer count = commentReplyService.lambdaQuery ()
                .in (CommentReply::getRootId, commentRootIdList)
                .count ();
        
        return commentRootIdList.size () + count;
    }
    
    @Override
    public PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(RootCommentDetailDtoReq dtoReq) {
        // 获取父评论, 以分页的形式
        Page<CommentRoot> page = lambdaQuery ()
                .eq (CommentRoot::getAnswerType, dtoReq.getAnswerType ())
                .eq (CommentRoot::getAnswerId, dtoReq.getAnswerId ())
                .orderByDesc (CommentRoot::getLikeCount)
                .orderByDesc (CommentRoot::getReplyCount)
                .orderByDesc (CommentRoot::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
    
        // 转换为 RootCommentDetailDtoResult 格式
        PageDtoResult<RootCommentDetailDtoResult> pageDtoResult = PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, RootCommentDetailDtoResult.class)
        );
        
        // 如果父评论不为空
        if (CollectionUtil.isNotEmpty (pageDtoResult.getRecords ())) {
            // 获取子评论明细
            List<Long> rootCommentIdList = pageDtoResult.getRecords ()
                    .stream ()
                    .map (RootCommentDetailDtoResult::getRootId)
                    .collect (Collectors.toList ());
            Map<Long, List<SonCommentDetailDtoResult>> sonCommentDetail = sonCommentDetail (rootCommentIdList);
        
            // 设置父评论的子评论
            pageDtoResult.getRecords ().parallelStream ().forEach (o ->
                    o.setSonCommentDetailList (sonCommentDetail.getOrDefault (o.getRootId (), Collections.emptyList ())));
        }
        return pageDtoResult;
    }
    
    @Override
    public PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq dtoReq) {
        // 获取子评论, 以分页的形式
        Page<CommentReply> page = commentReplyService.lambdaQuery ()
                .eq (CommentReply::getRootId, dtoReq.getRootId ())
                .orderByAsc (CommentReply::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, SonCommentDetailDtoResult.class)
        );
    }
    
    @Override
    public void likeRootComment(LikeDtoReq dtoReq) {
        // 获取当前点赞数
        CommentRoot commentRoot = getById (dtoReq.getSourceId ());
        commentRoot.setLikeCount (dtoReq.getLikeCount ());
    
        // 更新
        Assert.isTrue (updateById (commentRoot), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void likeSonComment(LikeDtoReq dtoReq) {
        // 获取当前点赞数
        CommentReply commentReply = commentReplyService.getById (dtoReq.getSourceId ());
        commentReply.setLikeCount (dtoReq.getLikeCount ());
    
        // 更新
        Assert.isTrue (commentReplyService.updateById (commentReply), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Boolean verifyRootComment(Long rootCommentId) {
        return lambdaQuery ()
                .eq (CommentRoot::getRootId, rootCommentId)
                .count () > 0;
    }
    
    @Override
    public Boolean verifySonComment(Long sonCommentId) {
        return commentReplyService.lambdaQuery ()
                .eq (CommentReply::getReplyId, sonCommentId)
                .count () > 0;
    }
    
    @Override
    public RootCommentDetailDtoResult getRootComment(Long rootCommentId) {
        CommentRoot commentRoot = getById (rootCommentId);
        Assert.notNull (commentRoot, new BizException ("该评论不存在!"));
        
        return BeanUtil.toBean  (commentRoot, RootCommentDetailDtoResult.class);
    }
    
    @Override
    public SonCommentDetailDtoResult getSonComment(Long sonCommentId) {
        CommentReply commentReply = commentReplyService.getById (sonCommentId);
        Assert.notNull (commentReply, new BizException ("该评论不存在!"));
    
        return BeanUtil.toBean  (commentReply, SonCommentDetailDtoResult.class);
    }
    
    /**
     * 获取父评论的所有子评论, 以map形式返回
     *
     * @param rootCommentIdList 父评论 ID
     * @return Map 集合, rootCommentId => key, 子评论列表 => value
     */
    private Map<Long, List<SonCommentDetailDtoResult>> sonCommentDetail (List<Long> rootCommentIdList) {
        if (CollectionUtil.isEmpty (rootCommentIdList)) {
            return MapUtil.empty ();
        }
        return commentReplyService.lambdaQuery ()
                .in (CommentReply::getRootId, rootCommentIdList)
                .orderByAsc (CommentReply::getCreateTime)
                .list ()
                .stream()
                .map (o -> BeanUtil.toBean (o, SonCommentDetailDtoResult.class))
                .collect (Collectors.groupingBy (
                        SonCommentDetailDtoResult::getRootId,
                        HashMap::new,
                        Collectors.toList ()));
    }
    
    /**
     * 更新父评论回复数量
     */
    @Async
    public void modifyRootCommentReply (Long rootCommentId) {
        CommentRoot commentRoot = baseMapper.selectById (rootCommentId);
        Integer replyCount = commentReplyService.lambdaQuery ()
                .eq (CommentReply::getRootId, rootCommentId)
                .count ();
        commentRoot.setReplyCount (replyCount);
        baseMapper.updateById (commentRoot);
    }
    
    @Async
    public void modifyAnswerCommentCount (Long answerId, String answerType) {
        Integer count = commentCount (new QuestionCommentCountDtoReq (answerId, answerType));
        if (AnswerTypeEnums.QUESTION.getType ().equals (answerType)) {
            questionService.modifyQuestionCommentCount (answerId, count);
        } else if (AnswerTypeEnums.NOTE.getType ().equals (answerType)) {
            noteService.modifyNoteCommentCount (answerId, count);
        }
    }
}
