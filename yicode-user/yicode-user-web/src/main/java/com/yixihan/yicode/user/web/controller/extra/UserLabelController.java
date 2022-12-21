package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLabelDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLabelDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserLabelApi;
import com.yixihan.yicode.user.biz.service.extra.UserLabelService;
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
 * @since 2022-12-21
 */
@Slf4j
@RestController
public class UserLabelController implements UserLabelApi {
    
    @Resource
    private UserLabelService userLabelService;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addUserLabel(ModifyUserLabelDtoReq dtoReq) {
        return ApiResult.create (userLabelService.addUserLabel (dtoReq));
    }
    
    @Override
    public ApiResult<List<UserLabelDtoResult>> getUserLabel(Long userId) {
        return ApiResult.create (userLabelService.getUserLabel (userId));
    }
}
