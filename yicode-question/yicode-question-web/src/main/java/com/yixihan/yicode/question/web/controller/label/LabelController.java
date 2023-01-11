package com.yixihan.yicode.question.web.controller.label;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.reset.label.LabelApi;
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
    public ApiResult<CommonDtoResult<Boolean>> addLabel(String labelName) {
        return ApiResult.create (service.addLabel (labelName));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delLabel(Long labelId) {
        return ApiResult.create (service.delLabel (labelId));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> labelDetail(List<Long> labelIdList) {
        return ApiResult.create (service.labelDetail (labelIdList));
    }
}
