package com.yixihan.yicode.question.biz.service.note.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.LikeDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.ModifyNoteDtoReq;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.biz.service.note.NoteService;
import com.yixihan.yicode.question.biz.service.question.QuestionService;
import com.yixihan.yicode.question.dal.mapper.note.NoteMapper;
import com.yixihan.yicode.question.dal.pojo.note.Note;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    @Resource
    private QuestionService questionService;
    
    @Override
    public NoteDtoResult addNote(ModifyNoteDtoReq dtoReq) {
        Note note = BeanUtil.toBean (dtoReq, Note.class);
    
        // 保存
        Assert.isTrue (save (note), BizCodeEnum.FAILED_TYPE_BUSINESS);

        // 异步更新问题题解数
        modifyQuestionNoteCount(dtoReq.getQuestionId ());
        return BeanUtil.toBean (note, NoteDtoResult.class);
    }
    
    @Override
    public NoteDtoResult modifyNote(ModifyNoteDtoReq dtoReq) {
        Note note = BeanUtil.toBean (dtoReq, Note.class);
        // 获取乐观锁
        Integer version = lambdaQuery ()
                .select (Note::getVersion)
                .eq (Note::getNoteId, dtoReq.getNoteId ())
                .one ()
                .getVersion ();
        Assert.notNull (version, new BizException ("该题解不存在！"));
        note.setVersion (version);
    
        // 更新
        Assert.isTrue (updateById (note), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        return noteDetail (dtoReq.getNoteId ());
    }
    
    @Override
    public void delNote(List<Long> noteIdList) {
        List<Note> noteList = baseMapper.selectBatchIds (noteIdList);
    
        // 删除
        Assert.isTrue (removeByIds (noteIdList), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        // 异步更新问题题解数
        noteList.parallelStream ().forEach (item -> modifyQuestionNoteCount(item.getQuestionId ()));
    }
    
    @Override
    public void likeNote(LikeDtoReq dtoReq) {
        Note note = getById (dtoReq.getSourceId ());
        note.setLikeCount (dtoReq.getLikeCount ());
    
        // 更新
        Assert.isTrue (updateById (note), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Integer questionNoteCount(Long questionId) {
        return lambdaQuery ()
                .eq (Note::getQuestionId, questionId)
                .count ();
    }
    
    @Override
    public NoteDtoResult noteDetail(Long noteId) {
        Note note = baseMapper.selectById (noteId);
        
        Assert.notNull (note, new BizException ("该题解不存在!"));
        
        // 更新阅读数
        note.setReadCount (note.getReadCount () + 1);
        Assert.isTrue (updateById (note), BizCodeEnum.FAILED_TYPE_BUSINESS);
        return BeanUtil.toBean (note, NoteDtoResult.class);
    }
    
    @Override
    public PageDtoResult<NoteDtoResult> queryNote(QueryNoteDtoReq dtoReq) {
        Page<NoteDtoResult> dtoResultPage = baseMapper.queryNote (dtoReq, PageUtil.toPage (dtoReq));
    
        return PageUtil.pageToPageDtoResult (
                dtoResultPage,
                o -> BeanUtil.toBean (o, NoteDtoResult.class)
        );
    }
    
    @Override
    public Boolean verifyNote(Long noteId) {
        return lambdaQuery ()
                .eq (Note::getNoteId, noteId)
                .count () > 0;
    }
    
    @Override
    public void modifyNoteCommentCount(Long noteId, Integer commentCount) {
        Note note = getById (noteId);
        note.setCommentCount (commentCount);
    
        Assert.isTrue (updateById (note), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Map<Long, String> noteName(List<Long> noteIdList) {
        return listByIds (noteIdList)
                .stream ()
                .collect (Collectors.toMap (
                        Note::getNoteId,
                        Note::getNoteName,
                        (k1, k2) -> k1
                ));
    }
    
    @Async
    public void modifyQuestionNoteCount (Long questionId) {
        Integer count = questionNoteCount(questionId);
        
        questionService.modifyQuestionNoteCount (questionId, count);
    }
}
