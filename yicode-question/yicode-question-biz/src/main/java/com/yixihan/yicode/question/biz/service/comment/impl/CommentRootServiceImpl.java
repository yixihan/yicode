package com.yixihan.yicode.question.biz.service.comment.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddRootCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddSonCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.RootCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.SonCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.biz.service.comment.CommentRootService;
import com.yixihan.yicode.question.dal.mapper.comment.CommentRootMapper;
import com.yixihan.yicode.question.dal.pojo.comment.CommentRoot;
import org.springframework.stereotype.Service;

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
    
    @Override
    public CommonDtoResult<Boolean> addRootComment(AddRootCommentDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> addSonComment(AddSonCommentDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delRootComment(Long rootCommentId) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delSonComment(Long sunCommentId) {
        return null;
    }
    
    @Override
    public PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(RootCommentDetailDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> likeRootComment(LikeDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> likeSonComment(LikeDtoReq dtoReq) {
        return null;
    }
}
