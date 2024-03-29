package com.yixihan.yicode.question.web.controller.comment;

import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.*;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.api.rest.comment.CommentApi;
import com.yixihan.yicode.question.biz.service.comment.CommentRootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 评论模块 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:30
 */
@Slf4j
@RestController
public class CommentController implements CommentApi {
    
    @Resource
    private CommentRootService service;
    
    @Override
    public ApiResult<RootCommentDetailDtoResult> addRootComment(AddRootCommentDtoReq dtoReq) {
        return ApiResult.create (service.addRootComment (dtoReq));
    }
    
    @Override
    public ApiResult<SonCommentDetailDtoResult> addSonComment(AddSonCommentDtoReq dtoReq) {
        return ApiResult.create (service.addSonComment (dtoReq));
    }
    
    @Override
    public void delRootComment(Long rootCommentId) {
        service.delRootComment (rootCommentId);
    }
    
    @Override
    public void delSonComment(Long sunCommentId) {
        service.delSonComment (sunCommentId);
    }
    
    @Override
    public ApiResult<Integer> commentCount(QuestionCommentCountDtoReq dtoReq) {
        return ApiResult.create (service.commentCount (dtoReq));
    }
    
    @Override
    public ApiResult<PageDtoResult<RootCommentDetailDtoResult>> rootCommentDetail(RootCommentDetailDtoReq dtoReq) {
        return ApiResult.create (service.rootCommentDetail (dtoReq));
    }
    
    @Override
    public ApiResult<PageDtoResult<SonCommentDetailDtoResult>> sonCommentDetail(SonCommentDetailDtoReq dtoReq) {
        return ApiResult.create (service.sonCommentDetail (dtoReq));
    }
    
    @Override
    public void likeRootComment(LikeDtoReq dtoReq) {
        service.likeRootComment (dtoReq);
    }
    
    @Override
    public void likeSonComment(LikeDtoReq dtoReq) {
        service.likeSonComment (dtoReq);
    }
    
    @Override
    public ApiResult<Boolean> verifyRootComment(Long rootCommentId) {
        return ApiResult.create (service.verifyRootComment (rootCommentId));
    }
    
    @Override
    public ApiResult<Boolean> verifySonComment(Long sonCommentId) {
        return ApiResult.create (service.verifySonComment (sonCommentId));
    }
    
    @Override
    public ApiResult<RootCommentDetailDtoResult> getRootComment(Long rootCommentId) {
        return ApiResult.create (service.getRootComment (rootCommentId));
    }
    
    @Override
    public ApiResult<SonCommentDetailDtoResult> getSonComment(Long sonCommentId) {
        return ApiResult.create (service.getSonComment (sonCommentId));
    }
}
