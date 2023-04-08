package com.yixihan.yicode.question.biz.service.label.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelNoteService;
import com.yixihan.yicode.question.biz.service.label.LabelService;
import com.yixihan.yicode.question.dal.mapper.label.LabelNoteMapper;
import com.yixihan.yicode.question.dal.pojo.label.LabelNote;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Transactional(rollbackFor = BizException.class)
    public List<LabelDtoResult>modifyNoteLabel(ModifyLabelNoteDtoReq dtoReq) {
        // 新建未有标签
        List<Long> newLabelIdList = labelService.addLabelBatch (dtoReq.getLabelNameList ());

        // 删除已有标签
        List<Long> idList = lambdaQuery()
                .select(LabelNote::getId)
                .eq(LabelNote::getNoteId, dtoReq.getNoteId())
                .list()
                .stream()
                .map(LabelNote::getId)
                .collect(Collectors.toList());
        Assert.isTrue (removeByIds (idList), BizCodeEnum.FAILED_TYPE_BUSINESS);

        // 保存标签
        dtoReq.getLabelIdList ().addAll (newLabelIdList);

        if (CollUtil.isNotEmpty(dtoReq.getLabelIdList())) {

            List<LabelNote> labelNoteList = new ArrayList<>(dtoReq.getLabelIdList().size());

            dtoReq.getLabelIdList().forEach(item -> {
                LabelNote labelNote = new LabelNote();
                labelNote.setNoteId(dtoReq.getNoteId());
                labelNote.setLabelId(item);
                labelNoteList.add(labelNote);
            });

            Assert.isTrue(saveBatch(labelNoteList), BizCodeEnum.FAILED_TYPE_BUSINESS);

        }
        return noteLabelDetail (dtoReq.getNoteId ());
    }

    @Override
    public List<LabelDtoResult> noteLabelDetail(Long noteId) {
        // 获取标签 id
        List<Long> labelIdList = lambdaQuery ()
                .select(LabelNote::getLabelId)
                .eq (LabelNote::getNoteId, noteId)
                .orderByDesc (LabelNote::getCreateTime)
                .list ()
                .stream ()
                .map (LabelNote::getLabelId)
                .collect (Collectors.toList ());

        return labelService.labelDetail (labelIdList);
    }

    @Override
    public List<LabelDtoResult> allNoteLabel(String labelName) {
        return baseMapper.allNoteLabel (labelName);
    }
}
