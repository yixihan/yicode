package com.yixihan.yicode.question.biz.service.note;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.dal.pojo.note.Note;

import java.util.List;
import java.util.Map;

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
     * @return {@link NoteDtoResult}
     */
    NoteDtoResult addNote(ModifyNoteDtoReq dtoReq);
    
    /**
     * 修改题解
     *
     * @param dtoReq 请求参数
     * @return {@link NoteDtoResult}
     */
    NoteDtoResult modifyNote(ModifyNoteDtoReq dtoReq);
    
    /**
     * 删除题解
     *
     * @param noteIdList 题解 ID
     */
    void delNote(List<Long> noteIdList);
    
    /**
     * 点赞题解
     *
     * @param dtoReq 请求参数
     */
    void likeNote(LikeDtoReq dtoReq);
    
    /**
     * 获取问题题解数量
     *
     * @param questionId 问题 ID
     * @return 题解数量
     */
    Integer questionNoteCount (Long questionId);
    
    /**
     * 查看题解
     *
     * @param noteId 题解 ID
     * @return {@link NoteDtoResult}
     */
    NoteDtoResult noteDetail(Long noteId);
    
    /**
     * 搜索题解
     *
     * @param dtoReq 请求参数
     * @return {@link NoteDtoResult}
     */
    PageDtoResult<NoteDtoResult> queryNote(QueryNoteDtoReq dtoReq);
    
    /**
     * 校验题解 ID 是否存在
     *
     * @param noteId 题解 ID
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifyNote(Long noteId);
    
    /**
     * 更新题解评论数
     *
     * @param noteId 题解 ID
     * @param commentCount 评论数量
     */
    void modifyNoteCommentCount(Long noteId, Integer commentCount);
    
    /**
     * 获取题解名字
     *
     * @param noteIdList 问题 ID
     * @return 题解 id-题解名字
     */
    Map<Long, String> noteName(List<Long> noteIdList);
}
