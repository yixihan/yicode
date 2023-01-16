package com.yixihan.yicode.question.openapi.api.rest.note;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.ModifyNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.QueryNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.response.note.NoteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题解 OpenApi
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Api(tags = "题解 OpenApi")
@RequestMapping("/open/note")
public interface NoteOpenApi {
    
    @ApiOperation("添加题解")
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addNote (@RequestBody ModifyNoteReq req);
    
    @ApiOperation("修改题解")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> modifyNote (@RequestBody ModifyNoteReq req);
    
    @ApiOperation("删除题解")
    @DeleteMapping(value = "/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delNote (@RequestBody List<Long> noteIdList);
    
    @ApiOperation("点赞题解")
    @PostMapping(value = "/like", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> likeNote (@RequestBody LikeReq req);
    
    @ApiOperation("收藏问题")
    @PostMapping(value = "/collection", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> collectionNote(@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("取消收藏问题")
    @PostMapping(value = "/collection/cancel", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> cancelCollectionNote(@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("查看题解")
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<NoteVO> noteDetail (@RequestParam Long noteId);
    
    @ApiOperation("搜索题解")
    @PostMapping(value = "/query", produces = "application/json")
    JsonResponse<PageVO<NoteVO>> queryNote (@RequestBody QueryNoteReq req);

}
