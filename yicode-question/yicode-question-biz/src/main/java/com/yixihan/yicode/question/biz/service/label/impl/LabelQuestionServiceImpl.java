package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
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
    public List<LabelDtoResult> addQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        Label label = labelService.getById (dtoReq.getLabelId ());
    
        LabelQuestion labelQuestion = new LabelQuestion ();
        labelQuestion.setLabelId (label.getLabelId ());
        labelQuestion.setQuestionId (dtoReq.getQuestionId ());
    
        // 保存
        Assert.isTrue (save (labelQuestion), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        return questionLabelDetail (dtoReq.getQuestionId ());
    }
    
    @Override
    public List<LabelDtoResult> delQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        Long id = lambdaQuery ()
                .select (LabelQuestion::getId)
                .eq (LabelQuestion::getQuestionId, dtoReq.getQuestionId ())
                .eq (LabelQuestion::getLabelId, dtoReq.getLabelId ())
                .one ()
                .getId ();
        Assert.notNull (id, new BizException ("该标签不存在"));
    
        // 删除
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        return questionLabelDetail (dtoReq.getQuestionId ());
    }
    
    @Override
    public List<LabelDtoResult> questionLabelDetail(Long questionId) {
        // 获取标签 id
        List<Long> labelIdList = lambdaQuery ()
                .eq (LabelQuestion::getQuestionId, questionId)
                .orderByDesc (LabelQuestion::getCreateTime)
                .list ()
                .stream ()
                .map (LabelQuestion::getLabelId)
                .collect (Collectors.toList ());
    
        return labelService.labelDetail (labelIdList);
    }
    
    @Override
    public List<LabelDtoResult> allQuestionLabel() {
        // 获取标签 id
        List<Long> labelIdList = lambdaQuery ()
                .orderByDesc (LabelQuestion::getCreateTime)
                .list ()
                .stream ()
                .map (LabelQuestion::getLabelId)
                .collect (Collectors.toList ());
    
        return labelService.labelDetail (labelIdList);
    }
}
