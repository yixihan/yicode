package com.yixihan.yicode.question.api.reset.note;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
    ApiResult<CommonDtoResult<Boolean>> addNote (@RequestBody ModifyNoteDtoReq dtoReq);
    
    @ApiOperation("修改题解")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> modifyNote (@RequestBody ModifyNoteDtoReq dtoReq);
    
    @ApiOperation("删除题解")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delNote (@RequestBody Long noteId);
    
    @ApiOperation("点赞题解")
    @PostMapping(value = "/like", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> likeNote (@RequestBody LikeDtoReq dtoReq);
    
    @ApiOperation("查看题解")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<NoteDtoResult> noteDetail (@RequestBody Long noteId);
    
    @ApiOperation("搜索题解")
    @PostMapping(value = "/query", produces = "application/json")
    ApiResult<PageDtoResult<NoteDtoResult>> queryNote (@RequestBody QueryNoteDtoReq dtoReq);
}
