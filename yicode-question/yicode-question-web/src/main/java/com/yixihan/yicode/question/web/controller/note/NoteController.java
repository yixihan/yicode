package com.yixihan.yicode.question.web.controller.note;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.api.rest.note.NoteApi;
import com.yixihan.yicode.question.biz.service.note.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问题题解表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class NoteController implements NoteApi {
    
    @Resource
    private NoteService service;
    
    @Override
    public ApiResult<NoteDtoResult> addNote(ModifyNoteDtoReq dtoReq) {
        return ApiResult.create (service.addNote (dtoReq));
    }
    
    @Override
    public ApiResult<NoteDtoResult> modifyNote(ModifyNoteDtoReq dtoReq) {
        return ApiResult.create (service.modifyNote (dtoReq));
    }
    
    @Override
    public void delNote(List<Long> noteIdList) {
        service.delNote (noteIdList);
    }
    
    @Override
    public void likeNote(LikeDtoReq dtoReq) {
        service.likeNote (dtoReq);
    }
    
    @Override
    public ApiResult<Integer> questionNoteCount(Long questionId) {
        return ApiResult.create (service.questionNoteCount (questionId));
    }
    
    @Override
    public ApiResult<NoteDtoResult> noteDetail(Long noteId) {
        return ApiResult.create (service.noteDetail (noteId));
    }
    
    @Override
    public ApiResult<PageDtoResult<NoteDtoResult>> queryNote(QueryNoteDtoReq dtoReq) {
        return ApiResult.create (service.queryNote (dtoReq));
    }
    
    @Override
    public ApiResult<Boolean> verifyNote(Long noteId) {
        return ApiResult.create (service.verifyNote (noteId));
    }
    
    @Override
    public ApiResult<Map<Long, String>> noteName(List<Long> noteIdList) {
        return ApiResult.create (service.noteName (noteIdList));
    }
}
