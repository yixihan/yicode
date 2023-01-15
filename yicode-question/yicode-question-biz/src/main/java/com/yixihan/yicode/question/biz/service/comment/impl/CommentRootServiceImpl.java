package com.yixihan.yicode.question.biz.service.comment.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.*;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.biz.service.comment.CommentRootService;
import com.yixihan.yicode.question.dal.mapper.comment.CommentReplyMapper;
import com.yixihan.yicode.question.dal.mapper.comment.CommentRootMapper;
import com.yixihan.yicode.question.dal.pojo.comment.CommentReply;
import com.yixihan.yicode.question.dal.pojo.comment.CommentRoot;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private CommentReplyMapper commentReplyMapper;
    
    @Override
    public CommonDtoResult<Boolean> addRootComment(AddRootCommentDtoReq dtoReq) {
        CommentRoot commentRoot = BeanUtil.toBean (dtoReq, CommentRoot.class);
    
        int modify = baseMapper.insert (commentRoot);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> addSonComment(AddSonCommentDtoReq dtoReq) {
        CommentReply commentReply = BeanUtil.toBean (dtoReq, CommentReply.class);
    
        int modify = commentReplyMapper.insert (commentReply);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        // 更新父评论回复数量
        modifyRootCommentReply (commentReply.getRootId ());
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delRootComment(Long rootCommentId) {
        int modify = baseMapper.deleteById (rootCommentId);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delSonComment(Long sunCommentId) {
        CommentReply commentReply = commentReplyMapper.selectById (sunCommentId);
        int modify = commentReplyMapper.deleteById (sunCommentId);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        // 更新父评论回复数量
        modifyRootCommentReply (commentReply.getRootId ());
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Integer> questionCommentCount(QuestionCommentCountDtoReq dtoReq) {
        List<CommentRoot> commentRootList = baseMapper.selectList (new QueryWrapper<CommentRoot> ()
                .eq (CommentRoot.ANSWER_ID, dtoReq.getAnswerId ())
                .eq (CommentRoot.ANSWER_TYPE, dtoReq.getAnswerType ()));
    
        if (CollectionUtil.isEmpty (commentRootList)) {
            return new CommonDtoResult<> (NumConstant.NUM_0);
        }
    
        Integer count = commentReplyMapper.selectCount (new QueryWrapper<CommentReply> ()
                .in (CommentReply.ROOT_ID, commentRootList.stream ()
                        .map (CommentRoot::getRootId).collect(Collectors.toList())));
    
        return new CommonDtoResult<> (commentRootList.size () + count);
    }
    
    @Override
    public PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(RootCommentDetailDtoReq dtoReq) {
        // 获取父评论, 以分页的形式
        Page<CommentRoot> pages = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ()),
                new QueryWrapper<CommentRoot> ()
                        .eq (CommentRoot.ANSWER_ID, dtoReq.getAnswerId ())
                        .eq (CommentRoot.ANSWER_TYPE, dtoReq.getAnswerType ())
        );
    
        // 转换为 RootCommentDetailDtoResult 格式
        PageDtoResult<RootCommentDetailDtoResult> pageDtoResult = PageUtil.pageToPageDtoResult (
                pages,
                (o) -> CopyUtils.copySingle (RootCommentDetailDtoResult.class, o)
        );
        
        // 如果父评论不为空
        if (CollectionUtil.isNotEmpty (pageDtoResult.getRecords ())) {
            // 获取子评论明细
            Map<Long, List<SonCommentDetailDtoResult>> sonCommentDetail = sonCommentDetail (
                    pageDtoResult.getRecords ().stream ()
                            .map (RootCommentDetailDtoResult::getRootId)
                            .collect (Collectors.toList ())
            );
        
            // 设置父评论的子评论
            pageDtoResult.getRecords ().forEach ((o) -> o.setSonCommentDetailList (sonCommentDetail.get (o.getRootId ())));
        }
        return pageDtoResult;
    }
    
    @Override
    public PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq dtoReq) {
        // 获取子评论, 以分页的形式
        Page<CommentReply> pages = commentReplyMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ()),
                new QueryWrapper<CommentReply> ().eq (CommentReply.ROOT_ID, dtoReq.getRootId ())
        );
        
        return PageUtil.pageToPageDtoResult (
                pages,
                (o) -> CopyUtils.copySingle (SonCommentDetailDtoResult.class, o)
        );
    }
    
    @Override
    public CommonDtoResult<Boolean> likeRootComment(LikeDtoReq dtoReq) {
        CommentRoot commentRoot = baseMapper.selectById (dtoReq.getSourceId ());
        commentRoot.setLikeCount (dtoReq.getLikeCount ());
    
        int modify = baseMapper.updateById (commentRoot);
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> likeSonComment(LikeDtoReq dtoReq) {
        CommentReply commentReply = commentReplyMapper.selectById (dtoReq.getSourceId ());
        commentReply.setLikeCount (dtoReq.getLikeCount ());
        
    
        int modify = commentReplyMapper.updateById (commentReply);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> verifyRootComment(Long rootCommentId) {
        return new CommonDtoResult<> (baseMapper.selectCount (new QueryWrapper<CommentRoot> ()
                .eq (CommentRoot.ROOT_ID, rootCommentId)) > 0);
    }
    
    @Override
    public CommonDtoResult<Boolean> verifySonComment(Long sonCommentId) {
        return new CommonDtoResult<> (commentReplyMapper.selectCount (new QueryWrapper<CommentReply> ()
                .eq (CommentReply.REPLY_ID, sonCommentId)) > 0);
    }
    
    @Override
    public RootCommentDetailDtoResult getRootComment(Long rootCommentId) {
        return BeanUtil.toBean  (
                baseMapper.selectById (rootCommentId),
                RootCommentDetailDtoResult.class
        );
    }
    
    @Override
    public SonCommentDetailDtoResult getSonComment(Long sonCommentId) {
        return BeanUtil.toBean (
                commentReplyMapper.selectById (sonCommentId),
                SonCommentDetailDtoResult.class
        );
    }
    
    /**
     * 更新父评论回复数量
     */
    @Async
    public void modifyRootCommentReply (Long rootCommentId) {
        CommentRoot commentRoot = baseMapper.selectById (rootCommentId);
        Integer replyCount = commentReplyMapper.selectCount (new QueryWrapper<CommentReply> ()
                .eq (CommentReply.ROOT_ID, rootCommentId));
        commentRoot.setReplyCount (replyCount);
        baseMapper.updateById (commentRoot);
    }
    
    /**
     * 获取父评论的所有子评论, 以列表形式返回
     *
     * @param rootCommentIdList 父评论 ID
     * @return Map 集合, rootCommentId => key, 子评论列表 => value
     */
    private Map<Long, List<SonCommentDetailDtoResult>> sonCommentDetail (List<Long> rootCommentIdList) {
        if (CollectionUtil.isEmpty (rootCommentIdList)) {
            return new HashMap<> ();
        }
        return commentReplyMapper.selectList (new QueryWrapper<CommentReply> ()
                .in (CommentReply.ROOT_ID, rootCommentIdList)).stream ()
                .map ((o) -> CopyUtils.copySingle (SonCommentDetailDtoResult.class, o))
                .collect (Collectors.groupingBy (
                        SonCommentDetailDtoResult::getRootId,
                        HashMap::new,
                        Collectors.toList ()));
    }
}
