package com.yixihan.yicode.question.api.rest.comment;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.comment.*;
import com.yixihan.yicode.question.api.dto.response.comment.RootCommentDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.comment.SonCommentDetailDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 评论 api
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "评论 api")
@RequestMapping("/comment")
public interface CommentApi {
    
    @ApiOperation("添加父评论")
    @PostMapping(value = "/add/root", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addRootComment(@RequestBody AddRootCommentDtoReq dtoReq);
    
    @ApiOperation("添加子评论")
    @PostMapping(value = "/add/son", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addSonComment(@RequestBody AddSonCommentDtoReq dtoReq);
    
    @ApiOperation("删除父评论")
    @PostMapping(value = "/del/root", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delRootComment(@RequestBody Long rootCommentId);
    
    @ApiOperation("删除子评论")
    @PostMapping(value = "/del/son", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delSonComment(@RequestBody Long sunCommentId);
    
    @ApiOperation("获取评论数量")
    @PostMapping(value = "/all/count", produces = "application/json")
    ApiResult<CommonDtoResult<Integer>> commentCount(@RequestBody QuestionCommentCountDtoReq dtoReq);
    
    @ApiOperation("获取所有评论")
    @PostMapping(value = "/all/root", produces = "application/json")
    ApiResult<PageDtoResult<RootCommentDetailDtoResult>> rootCommentDetail(@RequestBody RootCommentDetailDtoReq dtoReq);
    
    @ApiOperation("获取父评论的所有子评论")
    @PostMapping(value = "/all/son", produces = "application/json")
    ApiResult<PageDtoResult<SonCommentDetailDtoResult>> sonCommentDetail(@RequestBody SonCommentDetailDtoReq dtoReq);
    
    @ApiOperation("点赞父评论")
    @PostMapping(value = "/like/root", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> likeRootComment(@RequestBody LikeDtoReq dtoReq);
    
    @ApiOperation("点赞子评论")
    @PostMapping(value = "/like/son", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> likeSonComment(@RequestBody LikeDtoReq dtoReq);
    
    
    
    
    @ApiOperation("校验父评论是否存在")
    @PostMapping(value = "/verify/root", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifyRootComment(@RequestBody Long rootCommentId);
    
    @ApiOperation("校验子评论是否存在")
    @PostMapping(value = "/verify/son", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifySonComment(@RequestBody Long sonCommentId);
    
    @ApiOperation("获取父评论")
    @PostMapping(value = "/detail/root", produces = "application/json")
    ApiResult<RootCommentDetailDtoResult> getRootComment(@RequestBody Long rootCommentId);
    
    @ApiOperation("获取子评论")
    @PostMapping(value = "/detail/son", produces = "application/json")
    ApiResult<SonCommentDetailDtoResult> getSonComment(@RequestBody Long sonCommentId);
}
