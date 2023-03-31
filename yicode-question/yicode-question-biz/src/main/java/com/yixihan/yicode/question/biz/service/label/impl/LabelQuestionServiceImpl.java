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
import com.yixihan.yicode.question.dal.pojo.label.LabelQuestion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Transactional(rollbackFor = BizException.class)
    public List<LabelDtoResult> modifyQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        // 新建未有标签
        List<Long> newLabelIdList = labelService.addLabelBatch (dtoReq.getLabelNameList ());
    
        // 保存标签
        dtoReq.getLabelIdList ().addAll (newLabelIdList);
        List<LabelQuestion> labelQuestionList = new ArrayList<> (dtoReq.getLabelIdList ().size ());
        
        dtoReq.getLabelIdList ().forEach (item -> {
            LabelQuestion labelQuestion = new LabelQuestion ();
            labelQuestion.setQuestionId (dtoReq.getQuestionId ());
            labelQuestion.setLabelId (item);
            labelQuestionList.add (labelQuestion);
        });
        
        Assert.isTrue (saveBatch (labelQuestionList), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
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
                .distinct ()
                .map (LabelQuestion::getLabelId)
                .collect (Collectors.toList ());
    
        return labelService.labelDetail (labelIdList);
    }
    
    @Override
    public List<LabelDtoResult> allQuestionLabel(String labelName) {
        return baseMapper.allQuestionLabel (labelName);
    }
}
