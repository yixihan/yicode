package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelNoteService;
import com.yixihan.yicode.question.biz.service.label.LabelService;
import com.yixihan.yicode.question.dal.mapper.label.LabelNoteMapper;
import com.yixihan.yicode.question.dal.pojo.label.Label;
import com.yixihan.yicode.question.dal.pojo.label.LabelNote;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 题解标签表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class LabelNoteServiceImpl extends ServiceImpl<LabelNoteMapper, LabelNote> implements LabelNoteService {
    
    @Resource
    private LabelService labelService;
    
    @Override
    public CommonDtoResult<Boolean> addNoteLabel(ModifyLabelNoteDtoReq dtoReq) {
        Label label = labelService.getById (dtoReq.getLabelId ());
        
        LabelNote labelNote = new LabelNote ();
        labelNote.setLabelId (label.getLabelId ());
        labelNote.setNoteId (dtoReq.getNoteId ());
    
        int modify = baseMapper.insert (labelNote);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delNoteLabel(ModifyLabelNoteDtoReq dtoReq) {
        int modify = baseMapper.delete (new QueryWrapper<LabelNote> ()
                .eq (LabelNote.NOTE_ID, dtoReq.getNoteId ())
                .eq (LabelNote.LABEL_ID, dtoReq.getLabelId ()));
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<LabelDtoResult> noteLabelDetail(Long noteId) {
        List<LabelNote> labelNoteList = baseMapper.selectList (new QueryWrapper<LabelNote> ()
                .eq (LabelNote.NOTE_ID, noteId));
    
        return labelService.labelDetail (labelNoteList.stream ()
                .map (LabelNote::getLabelId).collect (Collectors.toList ()));
    }
    
    @Override
    public List<LabelDtoResult> allNoteLabel() {
        List<Long> labelIdList = baseMapper.selectList (null).stream ()
                .map (LabelNote::getLabelId).collect (Collectors.toList ());
        
        return labelService.labelDetail (labelIdList);
    }
}
