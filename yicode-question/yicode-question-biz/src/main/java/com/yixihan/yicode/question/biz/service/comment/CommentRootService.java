package com.yixihan.yicode.question.biz.service.comment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.*;
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
     * @return {@link RootCommentDetailDtoResult}
     */
    RootCommentDetailDtoResult addRootComment(AddRootCommentDtoReq dtoReq);
    
    /**
     * 添加子评论
     *
     * @param dtoReq 请求参数
     * @return {@link SonCommentDetailDtoResult}
     */
    SonCommentDetailDtoResult addSonComment(AddSonCommentDtoReq dtoReq);
    
    /**
     * 删除父评论
     *
     * @param rootCommentId 请求参数
     */
    void delRootComment(Long rootCommentId);
    
    /**
     * 删除子评论
     *
     * @param sunCommentId 请求参数
     */
    void delSonComment(Long sunCommentId);
    
    /**
     * 获取问题评论数量
     *
     * @param dtoReq 请求参数
     * @return 评论数量
     */
    Integer commentCount(QuestionCommentCountDtoReq dtoReq);
    
    /**
     * 获取所有评论
     *
     * @param dtoReq 请求参数
     * @return {@link RootCommentDetailDtoResult}
     */
    PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(RootCommentDetailDtoReq dtoReq);
    
    /**
     * 获取父评论的所有子评论
     *
     * @param dtoReq 请求参数
     * @return {@link SonCommentDetailDtoResult}
     */
    PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq dtoReq);
    
    /**
     * 点赞父评论
     *
     * @param dtoReq 请求参数
     */
    void likeRootComment(LikeDtoReq dtoReq);
    
    /**
     * 点赞子评论
     *
     * @param dtoReq 请求参数
     */
    void likeSonComment(LikeDtoReq dtoReq);
    
    /**
     * 校验父评论是否存在
     *
     * @param rootCommentId 父评论 ID
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifyRootComment(Long rootCommentId);
    
    /**
     * 校验子评论是否存在
     *
     * @param sonCommentId 子评论 ID
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifySonComment(Long sonCommentId);
    
    /**
     * 获取父评论
     *
     * @param rootCommentId 父评论 ID
     * @return {@link RootCommentDetailDtoResult}
     */
    RootCommentDetailDtoResult getRootComment(Long rootCommentId);
    
    /**
     * 获取子评论
     *
     * @param sonCommentId 子评论 ID
     * @return {@link SonCommentDetailDtoResult}
     */
    SonCommentDetailDtoResult getSonComment(Long sonCommentId);
}
