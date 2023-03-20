package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
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
    public List<LabelDtoResult> addNoteLabel(ModifyLabelNoteDtoReq dtoReq) {
        Label label = labelService.getById (dtoReq.getLabelId ());
        
        LabelNote labelNote = new LabelNote ();
        labelNote.setLabelId (label.getLabelId ());
        labelNote.setNoteId (dtoReq.getNoteId ());
    
        // 保存
        Assert.isTrue (save (labelNote), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        return noteLabelDetail (dtoReq.getNoteId ());
    }
    
    @Override
    public List<LabelDtoResult> delNoteLabel(ModifyLabelNoteDtoReq dtoReq) {
        Long id = lambdaQuery ()
                .select (LabelNote::getId)
                .eq (LabelNote::getNoteId, dtoReq.getNoteId ())
                .eq (LabelNote::getLabelId, dtoReq.getNoteId ())
                .one ()
                .getId ();
        Assert.notNull (id, new BizException ("该标签不存在"));
        
        // 删除
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
        return noteLabelDetail (dtoReq.getNoteId ());
    }
    
    @Override
    public List<LabelDtoResult> noteLabelDetail(Long noteId) {
        // 获取标签 id
        List<Long> labelIdList = lambdaQuery ()
                .eq (LabelNote::getNoteId, noteId)
                .orderByDesc (LabelNote::getCreateTime)
                .list ()
                .stream ()
                .map (LabelNote::getLabelId)
                .collect (Collectors.toList ());
                
        return labelService.labelDetail (labelIdList);
    }
    
    @Override
    public List<LabelDtoResult> allNoteLabel() {
        List<Long> labelIdList = lambdaQuery ()
                .orderByDesc (LabelNote::getCreateTime)
                .list ()
                .stream ()
                .map (LabelNote::getLabelId)
                .collect (Collectors.toList ());
    
        return labelService.labelDetail (labelIdList);
    }
}
