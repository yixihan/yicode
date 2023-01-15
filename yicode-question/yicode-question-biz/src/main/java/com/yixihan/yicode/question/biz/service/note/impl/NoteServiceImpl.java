package com.yixihan.yicode.question.biz.service.note.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.biz.service.note.NoteService;
import com.yixihan.yicode.question.dal.mapper.note.NoteMapper;
import com.yixihan.yicode.question.dal.pojo.note.Note;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Note note = BeanUtil.toBean (dtoReq, Note.class);
    
        int modify = baseMapper.insert (note);
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> modifyNote(ModifyNoteDtoReq dtoReq) {
        Note note = BeanUtil.toBean (dtoReq, Note.class);
    
        int modify = baseMapper.updateById (note);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delNote(List<Long> noteIdList) {
        int modify = baseMapper.deleteBatchIds (noteIdList);
        
        if (modify < 0) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> likeNote(LikeDtoReq dtoReq) {
        Note note = baseMapper.selectById (dtoReq.getSourceId ());
        note.setLikeCount (dtoReq.getLikeCount ());
        
    
        int modify = baseMapper.updateById (note);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public NoteDtoResult noteDetail(Long noteId) {
        Note note = baseMapper.selectById (noteId);
        
        if (note != null) {
            note.setReadCount (note.getReadCount () + 1);
            baseMapper.updateById (note);
            return CopyUtils.copySingle (NoteDtoResult.class, note);
        }
        
        return new NoteDtoResult ();
    }
    
    @Override
    public PageDtoResult<NoteDtoResult> queryNote(QueryNoteDtoReq dtoReq) {
        Page<NoteDtoResult> dtoResultPage = baseMapper.queryNote (dtoReq,
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ()));
    
        return PageUtil.pageToPageDtoResult (
                dtoResultPage,
                (o) -> CopyUtils.copySingle (NoteDtoResult.class, o)
        );
    }
    
    @Override
    public CommonDtoResult<Boolean> verifyNote(Long noteId) {
        return new CommonDtoResult<> (baseMapper.selectCount (new QueryWrapper<Note> ()
                .eq (Note.NOTE_ID, noteId)) > 0);
    }
}
