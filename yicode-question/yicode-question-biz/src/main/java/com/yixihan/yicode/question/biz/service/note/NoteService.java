package com.yixihan.yicode.question.biz.service.note;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.dal.pojo.note.Note;

import java.util.List;

/**
 * <p>
 * 问题题解表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface NoteService extends IService<Note> {
    
    /**
     * 添加题解
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addNote(ModifyNoteDtoReq dtoReq);
    
    /**
     * 修改题解
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> modifyNote(ModifyNoteDtoReq dtoReq);
    
    /**
     * 添加题解
     *
     * @param noteIdList 题解 ID
     */
    CommonDtoResult<Boolean> delNote(List<Long> noteIdList);
    
    /**
     * 点赞题解
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> likeNote(LikeDtoReq dtoReq);
    
    /**
     * 查看题解
     *
     * @param noteId 题解 ID
     */
    NoteDtoResult noteDetail(Long noteId);
    
    /**
     * 搜索题解
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<NoteDtoResult> queryNote(QueryNoteDtoReq dtoReq);
}