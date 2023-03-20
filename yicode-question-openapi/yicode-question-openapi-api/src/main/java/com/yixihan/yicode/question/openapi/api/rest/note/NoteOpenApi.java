package com.yixihan.yicode.question.openapi.api.rest.note;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.ModifyLabelNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.ModifyNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.QueryNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
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
    JsonResponse<NoteVO> addNote (@RequestBody ModifyNoteReq req);
    
    @ApiOperation("修改题解")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<NoteVO> modifyNote (@RequestBody ModifyNoteReq req);
    
    @ApiOperation("删除题解")
    @DeleteMapping(value = "/del", produces = "application/json")
    void delNote (@RequestBody List<Long> noteIdList);
    
    @ApiOperation("点赞题解")
    @PostMapping(value = "/like", produces = "application/json")
    void likeNote (@RequestBody LikeReq req);
    
    @ApiOperation("收藏问题")
    @PostMapping(value = "/collection", produces = "application/json")
    void collectionNote(@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("取消收藏问题")
    @DeleteMapping(value = "/collection/cancel", produces = "application/json")
    void cancelCollectionNote(@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("添加题解标签")
    @PostMapping(value = "/label/add", produces = "application/json")
    JsonResponse<List<LabelVO>> addNoteLabel(@RequestBody ModifyLabelNoteReq req);
    
    @ApiOperation("删除题解标签")
    @DeleteMapping(value = "/label/del", produces = "application/json")
    JsonResponse<List<LabelVO>> delNoteLabel(@RequestBody ModifyLabelNoteReq req);
    
    @ApiOperation("查看题解")
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<NoteVO> noteDetail (@RequestParam Long noteId);
    
    @ApiOperation("搜索题解")
    @PostMapping(value = "/query", produces = "application/json")
    JsonResponse<PageVO<NoteVO>> queryNote (@RequestBody QueryNoteReq req);

}
