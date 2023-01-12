package com.yixihan.yicode.question.web.controller.label;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.reset.label.LabelNoteApi;
import com.yixihan.yicode.question.biz.service.label.LabelNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 题解标签表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Slf4j
@RestController
public class LabelNoteController implements LabelNoteApi {
    
    @Resource
    private LabelNoteService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addNoteLabel(String noteLabelName) {
        return ApiResult.create (service.addNoteLabel (noteLabelName));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delNoteLabel(Long noteLabelId) {
        return ApiResult.create (service.delNoteLabel (noteLabelId));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> noteLabelDetail(Long noteId) {
        return ApiResult.create (service.noteLabelDetail (noteId));
    }
    
    @Override
    public ApiResult<List<LabelDtoResult>> allNoteLabel() {
        return ApiResult.create (service.allNoteLabel ());
    }
}
