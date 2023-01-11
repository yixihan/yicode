package com.yixihan.yicode.question.web.controller.comment;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.comment.AddRootCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.AddSonCommentDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.RootCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.SonCommentDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import com.yixihan.yicode.question.api.reset.comment.CommentApi;
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
    public ApiResult<CommonDtoResult<Boolean>> addRootComment(AddRootCommentDtoReq dtoReq) {
        return ApiResult.create (service.addRootComment (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addSonComment(AddSonCommentDtoReq dtoReq) {
        return ApiResult.create (service.addSonComment (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delRootComment(Long rootCommentId) {
        return ApiResult.create (service.delRootComment (rootCommentId));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delSonComment(Long sunCommentId) {
        return ApiResult.create (service.delSonComment (sunCommentId));
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
    public ApiResult<CommonDtoResult<Boolean>> likeRootComment(Long rootCommentId) {
        return ApiResult.create (service.likeRootComment (rootCommentId));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> likeSonComment(Long sunCommentId) {
        return ApiResult.create (service.likeSonComment (sunCommentId));
    }
}
