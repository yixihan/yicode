package com.yixihan.yicode.question.openapi.web.controller.comment;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.comment.CommentOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddRootCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.AddSonCommentReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.RootCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.request.comment.SonCommentDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.RootCommentDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.comment.SonCommentDetailVO;
import com.yixihan.yicode.question.openapi.biz.service.comment.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 评论 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class CommentController implements CommentOpenApi {
    
    @Resource
    private CommentService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addRootComment(AddRootCommentReq req) {
        return JsonResponse.ok (service.addRootComment (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addSonComment(AddSonCommentReq req) {
        return JsonResponse.ok (service.addSonComment (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delRootComment(Long rootCommentId) {
        return JsonResponse.ok (service.delRootComment (rootCommentId));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delSonComment(Long sunCommentId) {
        return JsonResponse.ok (service.delSonComment (sunCommentId));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> likeRootComment(LikeReq req) {
        return JsonResponse.ok (service.likeRootComment (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> likeSonComment(LikeReq req) {
        return JsonResponse.ok (service.likeSonComment (req));
    }
    
    @Override
    public JsonResponse<PageVO<RootCommentDetailVO>> rootCommentDetail(RootCommentDetailReq req) {
        return JsonResponse.ok (service.rootCommentDetail (req));
    }
    
    @Override
    public JsonResponse<PageVO<SonCommentDetailVO>> sonCommentDetail(SonCommentDetailReq req) {
        return JsonResponse.ok (service.sonCommentDetail (req));
    }
}
