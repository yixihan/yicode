package com.yixihan.yicode.question.biz.service.note.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.biz.service.note.NoteService;
import com.yixihan.yicode.question.dal.mapper.note.NoteMapper;
import com.yixihan.yicode.question.dal.pojo.note.Note;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题题解表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {
    
    @Override
    public CommonDtoResult<Boolean> addNote(ModifyNoteDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyNote(ModifyNoteDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delNote(Long noteId) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> likeNote(LikeDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public NoteDtoResult noteDetail(Long noteId) {
        return null;
    }
    
    @Override
    public PageDtoResult<NoteDtoResult> queryNote(QueryNoteDtoReq dtoReq) {
        return null;
    }
}
