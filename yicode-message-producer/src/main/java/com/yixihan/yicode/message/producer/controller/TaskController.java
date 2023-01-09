package com.yixihan.yicode.message.producer.controller;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.message.producer.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.message.producer.reset.TaskApi;
import com.yixihan.yicode.message.producer.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 任务系统 前端控制器
 *
 * @author yixihan
 * @date 2022-10-27-23:58
 */
@Slf4j
@RestController
public class TaskController implements TaskApi {

    @Resource
    private MessageService service;
    
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> send(MsgSendDtoReq dtoReq) {
        return ApiResult.create (service.sendTask (dtoReq));
    }
}
