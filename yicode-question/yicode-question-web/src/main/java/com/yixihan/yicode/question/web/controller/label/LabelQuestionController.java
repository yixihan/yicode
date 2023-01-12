package com.yixihan.yicode.question.web.controller.label;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.reset.label.LabelQuestionApi;
import com.yixihan.yicode.question.biz.service.label.LabelQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 问题标签表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class LabelQuestionController implements LabelQuestionApi {
    
    @Resource
    private LabelQuestionService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addQuestionLabel(String questionLabelName) {
        return ApiResult.create (service.addQuestionLabel (questionLabelName));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delQuestionLabel(Long questionLabelId) {
        return ApiResult.create (service.delQuestionLabel (questionLabelId));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> questionLabelDetail(Long questionId) {
        return ApiResult.create (service.questionLabelDetail (questionId));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> allQuestionLabel() {
        return ApiResult.create (service.allQuestionLabel ());
    }
}
