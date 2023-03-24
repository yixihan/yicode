package com.yixihan.yicode.question.web.controller.label;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.rest.label.LabelQuestionApi;
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
    public ApiResult<List<LabelDtoResult>> addQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        return ApiResult.create (service.addQuestionLabel (dtoReq));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> delQuestionLabel(ModifyLabelQuestionDtoReq dtoReq) {
        return ApiResult.create (service.delQuestionLabel (dtoReq));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> questionLabelDetail(Long questionId) {
        return ApiResult.create (service.questionLabelDetail (questionId));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> allQuestionLabel(String labelName) {
        return ApiResult.create (service.allQuestionLabel (labelName));
    }
}
