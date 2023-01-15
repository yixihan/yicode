package com.yixihan.yicode.question.openapi.biz.service.note;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.ModifyNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.request.note.QueryNoteReq;
import com.yixihan.yicode.question.openapi.api.vo.response.note.NoteVO;

import java.util.List;

/**
 * 题解 服务类
 *
 * @author yixihan
 * @date 2023/1/13 12:18
 */
public interface NoteService {
    
    /**
     * 添加题解
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> addNote(ModifyNoteReq req);
    
    /**
     * 修改题解
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> modifyNote(ModifyNoteReq req);
    
    /**
     * 删除题解
     *
     * @param noteIdList 题解 ID
     */
    CommonVO<Boolean> delNote(List<Long> noteIdList);
    
    /**
     * 点赞题解
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> likeNote(LikeReq req);
    
    /**
     * 题解明细
     *
     * @param noteId 题解 ID
     */
    NoteVO noteDetail(Long noteId);
    
    /**
     * 搜索题解
     *
     * @param req 请求参数
     */
    PageVO<NoteVO> queryNote(QueryNoteReq req);
}
