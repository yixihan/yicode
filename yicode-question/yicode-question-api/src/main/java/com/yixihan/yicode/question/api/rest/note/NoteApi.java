package com.yixihan.yicode.question.api.rest.note;

import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 题解 api
 *
 * @author yixihan
 * @date 2023/1/11 10:17
 */
@Api(tags = "题解 api")
@RequestMapping("/note")
public interface NoteApi {
    
    @ApiOperation("添加题解")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<NoteDtoResult> addNote (@RequestBody ModifyNoteDtoReq dtoReq);
    
    @ApiOperation("修改题解")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<NoteDtoResult> modifyNote (@RequestBody ModifyNoteDtoReq dtoReq);
    
    @ApiOperation("删除题解")
    @PostMapping(value = "/del", produces = "application/json")
    void delNote (@RequestBody List<Long> noteIdList);
    
    @ApiOperation("点赞题解")
    @PostMapping(value = "/like", produces = "application/json")
    void likeNote (@RequestBody LikeDtoReq dtoReq);
    
    @ApiOperation("获取问题题解总数")
    @PostMapping(value = "/count", produces = "application/json")
    ApiResult<Integer> questionNoteCount(Long questionId);
    
    @ApiOperation("查看题解")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<NoteDtoResult> noteDetail (@RequestBody Long noteId);
    
    @ApiOperation("搜索题解")
    @PostMapping(value = "/query", produces = "application/json")
    ApiResult<PageDtoResult<NoteDtoResult>> queryNote (@RequestBody QueryNoteDtoReq dtoReq);
    
    
    @ApiOperation("校验题解 ID 是否存在")
    @PostMapping(value = "/verify", produces = "application/json")
    ApiResult<Boolean> verifyNote (@RequestBody Long noteId);
    
    @ApiOperation ("获取题解名字")
    @PostMapping(value = "/detail/name", produces = "application/json")
    ApiResult<Map<Long, String>> noteName(@RequestBody List<Long> noteIdList);
}
