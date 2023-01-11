package com.yixihan.yicode.question.biz.service.comment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.comment.AddRootCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddSonCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.RootCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.SonCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.dal.pojo.comment.CommentRoot;

/**
 * <p>
 * 父评论表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface CommentRootService extends IService<CommentRoot> {
    
    /**
     * 添加父评论
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addRootComment(AddRootCommentDtoReq dtoReq);
    
    /**
     * 添加子评论
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addSonComment(AddSonCommentDtoReq dtoReq);
    
    /**
     * 删除父评论
     *
     * @param rootCommentId 请求参数
     */
    CommonDtoResult<Boolean> delRootComment(Long rootCommentId);
    
    /**
     * 删除子评论
     *
     * @param sunCommentId 请求参数
     */
    CommonDtoResult<Boolean> delSonComment(Long sunCommentId);
    
    /**
     * 获取所有评论
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(RootCommentDetailDtoReq dtoReq);
    
    /**
     * 获取父评论的所有子评论
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq dtoReq);
    
    /**
     * 点赞父评论
     *
     * @param rootCommentId 父评论 ID
     */
    CommonDtoResult<Boolean> likeRootComment(Long rootCommentId);
    
    /**
     * 点赞子评论
     *
     * @param sunCommentId 子评论 ID
     */
    CommonDtoResult<Boolean> likeSonComment(Long sunCommentId);
}
