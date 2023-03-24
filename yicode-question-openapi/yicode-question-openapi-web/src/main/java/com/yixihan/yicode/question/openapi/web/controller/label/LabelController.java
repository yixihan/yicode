package com.yixihan.yicode.question.openapi.web.controller.label;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.label.LabelOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.label.AddLabelReq;
import com.yixihan.yicode.question.openapi.api.vo.request.label.QueryLabelReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
import com.yixihan.yicode.question.openapi.biz.service.label.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class LabelController implements LabelOpenApi {
    
    @Resource
    private LabelService service;
    
    @Override
    public JsonResponse<LabelVO> addLabel(AddLabelReq req) {
        return JsonResponse.ok (service.addLabel (req));
    }
    
    @Override
    public void delLabel(List<Long> labelIdList) {
        service.delLabel (labelIdList);
    }
    
    @Override
    public JsonResponse<List<LabelVO>> labelDetail(List<Long> labelIdList) {
        return JsonResponse.ok (service.labelDetail (labelIdList));
    }
    
    @Override
    public JsonResponse<List<LabelVO>> noteLabelDetail(Long noteId) {
        return JsonResponse.ok (service.noteLabelDetail (noteId));
    }
    
    @Override
    public JsonResponse<List<LabelVO>> questionLabelDetail(Long questionId) {
        return JsonResponse.ok (service.questionLabelDetail (questionId));
    }
    
    @Override
    public JsonResponse<PageVO<LabelVO>> allLabel(QueryLabelReq req) {
        return JsonResponse.ok (service.allLabel (req));
    }
    
    @Override
    public JsonResponse<List<LabelVO>> allNoteLabel(String labelName) {
        return JsonResponse.ok (service.allNoteLabel (labelName));
    }
    
    @Override
    public JsonResponse<List<LabelVO>> allLabel(String labelName) {
        return JsonResponse.ok (service.allQuestionLabel (labelName));
    }
}
