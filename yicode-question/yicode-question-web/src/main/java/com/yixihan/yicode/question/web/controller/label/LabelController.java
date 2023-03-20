package com.yixihan.yicode.question.web.controller.label;


import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.AddLabelDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.rest.label.LabelApi;
import com.yixihan.yicode.question.biz.service.label.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class LabelController implements LabelApi {
    
    @Resource
    private LabelService service;
    
    @Override
    public ApiResult<LabelDtoResult> addLabel(AddLabelDtoReq dtoReq) {
        return ApiResult.create (service.addLabel (dtoReq));
    }
    
    @Override
    public void delLabel(List<Long> labelIdList) {
        service.delLabel (labelIdList);
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> labelDetail(List<Long> labelIdList) {
        return ApiResult.create (service.labelDetail (labelIdList));
    }
    
    @Override
    public ApiResult<PageDtoResult<LabelDtoResult>> allLabel(PageDtoReq dtoReq) {
        return ApiResult.create (service.allLabel (dtoReq));
    }
    
    @Override
    public ApiResult<Boolean> verifyLabel(Long labelId) {
        return ApiResult.create (service.verifyLabel (labelId));
    }
}
