package com.yixihan.yicode.question.openapi.biz.service.comment;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddRootCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddSonCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.RootCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.SonCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.RootCommentDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.SonCommentDetailVO;

/**
 * 评论 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface CommentService {
    
    /**
     * 添加父评论
     *
     * @param req 请求参数
     * @return {@link RootCommentDetailVO}
     */
    RootCommentDetailVO addRootComment(AddRootCommentReq req);
    
    /**
     * 添加子评论
     *
     * @param req 请求参数
     * @return {@link SonCommentDetailVO}
     */
    SonCommentDetailVO addSonComment(AddSonCommentReq req);
    
    /**
     * 删除父评论
     *
     * @param rootCommentId 父评论 ID
     */
    void delRootComment(Long rootCommentId);
    
    /**
     * 删除子评论
     *
     * @param sunCommentId 子评论 ID
     */
    void delSonComment(Long sunCommentId);
    
    /**
     * 点赞父评论
     *
     * @param req 请求参数
     */
    void likeRootComment(LikeReq req);
    
    /**
     * 点赞子评论
     *
     * @param req 请求参数
     */
    void likeSonComment(LikeReq req);
    
    /**
     * 获取所有评论
     *
     * @param req 请求参数
     * @return {@link RootCommentDetailVO}
     */
    PageVO<RootCommentDetailVO> rootCommentDetail(RootCommentDetailReq req);
    
    /**
     * 获取父评论的所有子评论
     *
     * @param req 请求参数
     * @return {@link SonCommentDetailVO}
     */
    PageVO<SonCommentDetailVO> sonCommentDetail(SonCommentDetailReq req);
}
