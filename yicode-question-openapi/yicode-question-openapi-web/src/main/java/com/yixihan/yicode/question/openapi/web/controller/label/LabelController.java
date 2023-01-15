package com.yixihan.yicode.question.openapi.web.controller.label;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.label.LabelOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.label.AddLabelReq;
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
    public JsonResponse<CommonVO<Boolean>> addLabel(AddLabelReq req) {
        return JsonResponse.ok (service.addLabel (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delLabel(List<Long> labelIdList) {
        return JsonResponse.ok (service.delLabel (labelIdList));
    }
    
    @Override
    public JsonResponse<List<LabelVO>> labelDetail(List<Long> labelIdList) {
        return JsonResponse.ok (service.labelDetail (labelIdList));
    }
}
