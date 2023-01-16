package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelQuestionService;
import com.yixihan.yicode.question.biz.service.label.LabelService;
import com.yixihan.yicode.question.dal.mapper.label.LabelQuestionMapper;
import com.yixihan.yicode.question.dal.pojo.label.Label;
import com.yixihan.yicode.question.dal.pojo.label.LabelQuestion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 问题标签表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class LabelQuestionServiceImpl extends ServiceImpl<LabelQuestionMapper, LabelQuestion> implements LabelQuestionService {
    
    @Resource
    private LabelService labelService;
    
    @Override
    public CommonDtoResult<Boolean> addQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        Label label = labelService.getById (dtoReq.getLabelId ());
    
        LabelQuestion labelQuestion = new LabelQuestion ();
        labelQuestion.setLabelId (label.getLabelId ());
        labelQuestion.setQuestionId (dtoReq.getQuestionId ());
    
        int modify = baseMapper.insert (labelQuestion);
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        int modify = baseMapper.delete (new QueryWrapper<LabelQuestion> ()
                .eq (LabelQuestion.QUESTION_ID, dtoReq.getQuestionId ())
                .eq (LabelQuestion.LABEL_ID, dtoReq.getLabelId ()));
    
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<LabelDtoResult> questionLabelDetail(Long questionId) {
        List<LabelQuestion> labelNoteList = baseMapper.selectList (new QueryWrapper<LabelQuestion> ()
                .eq (LabelQuestion.QUESTION_ID, questionId));
    
        return labelService.labelDetail (labelNoteList.stream ()
                .map (LabelQuestion::getLabelId).collect (Collectors.toList ()));
    }
    
    @Override
    public List<LabelDtoResult> allQuestionLabel() {
        List<Long> labelIdList = baseMapper.selectList (null).stream ()
                .map (LabelQuestion::getLabelId).collect (Collectors.toList ());
    
        return labelService.labelDetail (labelIdList);
    }
}
