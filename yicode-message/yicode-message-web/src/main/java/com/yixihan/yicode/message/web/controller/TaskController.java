package com.yixihan.yicode.message.web.controller;

import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.message.api.rest.TaskApi;
import com.yixihan.yicode.message.biz.service.MessageService;
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
    public void commit(MsgSendDtoReq dtoReq) {
        service.commit (dtoReq);
    }
    
    @Override
    public void test(MsgSendDtoReq dtoReq) {
        service.test (dtoReq);
    }
}
