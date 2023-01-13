package com.yixihan.yicode.question.openapi.biz.service.comment.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddRootCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddSonCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.RootCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.SonCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.RootCommentDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.SonCommentDetailVO;
import com.yixihan.yicode.question.openapi.biz.service.comment.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 评论 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:21
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    
    @Override
    public CommonVO<Boolean> addRootComment(AddRootCommentReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> addSonComment(AddSonCommentReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delRootComment(Long rootCommentId) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delSonComment(Long sunCommentId) {
        return null;
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
        return null;
    }
    
    @Override
    public PageVO<SonCommentDetailVO> sonCommentDetail(SonCommentDetailReq req) {
        return null;
    }
}
