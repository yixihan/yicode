package com.yixihan.yicode.question.openapi.web.controller.note;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.note.NoteOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.ModifyCollectionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.ModifyNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.QueryNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.response.note.NoteVO;
import com.yixihan.yicode.question.openapi.biz.service.note.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题解 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class NoteController implements NoteOpenApi {
    
    @Resource
    private NoteService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addNote(ModifyNoteReq req) {
        return JsonResponse.ok (service.addNote (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> modifyNote(ModifyNoteReq req) {
        return JsonResponse.ok (service.modifyNote (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delNote(List<Long> noteIdList) {
        return JsonResponse.ok (service.delNote (noteIdList));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> likeNote(LikeReq req) {
        return JsonResponse.ok (service.likeNote (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> collectionNote(ModifyCollectionReq req) {
        return JsonResponse.ok (service.collectionNote (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> cancelCollectionNote(ModifyCollectionReq req) {
        return JsonResponse.ok (service.cancelCollectionNote (req));
    }
    
    @Override
    public JsonResponse<NoteVO> noteDetail(Long noteId) {
        return JsonResponse.ok (service.noteDetail (noteId));
    }
    
    @Override
    public JsonResponse<PageVO<NoteVO>> queryNote(QueryNoteReq req) {
        return JsonResponse.ok (service.queryNote (req));
    }
}
