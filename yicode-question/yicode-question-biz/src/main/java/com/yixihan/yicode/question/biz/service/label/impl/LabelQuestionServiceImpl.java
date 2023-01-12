package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelQuestionService;
import com.yixihan.yicode.question.dal.mapper.label.LabelQuestionMapper;
import com.yixihan.yicode.question.dal.pojo.label.LabelQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

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
    
    @Override
    public CommonDtoResult<Boolean> addQuestionLabel(String questionLabelName) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delQuestionLabel(Long questionLabelId) {
        return null;
    }
    
    @Override
    public List<LabelDtoResult> questionLabelDetail(Long questionId) {
        return null;
    }
    
    @Override
    public List<LabelDtoResult> allQuestionLabel() {
        return null;
    }
}
