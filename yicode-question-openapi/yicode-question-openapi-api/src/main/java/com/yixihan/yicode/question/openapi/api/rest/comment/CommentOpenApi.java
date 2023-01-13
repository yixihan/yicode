package com.yixihan.yicode.question.openapi.api.rest.comment;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddRootCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddSonCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.RootCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.SonCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.RootCommentDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.SonCommentDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 评论 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "评论 OpenApi")
@RequestMapping("/open/comment")
public interface CommentOpenApi {
    
    @ApiOperation("添加父评论")
    @PostMapping(value = "/add/root", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addRootComment(@RequestBody AddRootCommentReq req);
    
    @ApiOperation("添加子评论")
    @PostMapping(value = "/add/son", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addSonComment(@RequestBody AddSonCommentReq req);
    
    @ApiOperation("删除父评论")
    @PostMapping(value = "/del/root", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delRootComment(@RequestBody Long rootCommentId);
    
    @ApiOperation("删除子评论")
    @PostMapping(value = "/del/son", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delSonComment(@RequestBody Long sunCommentId);
    
    @ApiOperation("点赞父评论")
    @PostMapping(value = "/lke/root", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> likeRootComment(@RequestBody LikeReq req);
    
    @ApiOperation("点赞子评论")
    @PostMapping(value = "/lke/son", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> likeSonComment(@RequestBody LikeReq req);
    
    @ApiOperation("获取所有评论")
    @PostMapping(value = "/detail/root", produces = "application/json")
    JsonResponse<PageVO<RootCommentDetailVO>> rootCommentDetail(@RequestBody RootCommentDetailReq req);
    
    @ApiOperation("获取父评论的所有子评论")
    @PostMapping(value = "/detail/son", produces = "application/json")
    JsonResponse<PageVO<SonCommentDetailVO>> sonCommentDetail(@RequestBody SonCommentDetailReq req);
}
