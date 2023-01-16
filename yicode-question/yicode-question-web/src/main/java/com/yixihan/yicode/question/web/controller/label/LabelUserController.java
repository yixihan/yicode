package com.yixihan.yicode.question.web.controller.label;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelUserDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.rest.label.LabelUserApi;
import com.yixihan.yicode.question.biz.service.label.LabelUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户标签表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class LabelUserController implements LabelUserApi {
    
    @Resource
    private LabelUserService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addUserLabel(ModifyLabelUserDtoReq dtoReq) {
        return ApiResult.create (service.addUserLabel (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delUserLabel(ModifyLabelUserDtoReq dtoReq) {
        return ApiResult.create (service.delUserLabel (dtoReq));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> userLabelDetail(Long userId) {
        return ApiResult.create (service.userLabelDetail (userId));
    }
}
