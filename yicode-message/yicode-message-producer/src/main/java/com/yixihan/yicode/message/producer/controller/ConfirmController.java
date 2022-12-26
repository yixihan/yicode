package com.yixihan.yicode.message.producer.controller;

import com.yixihan.yicode.message.producer.service.ConfirmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 发布确认-生产者
 *
 * @author yixihan
 * @date 2022-10-27-23:58
 */
@Slf4j
@RestController
@RequestMapping("/sendmsg/")
public class ConfirmController {

    @Resource
    private ConfirmService confirmService;

    @PostMapping(value = "/sendmessage", produces = "application/yaml")
    public void sendMessage (@RequestBody String message) {
        confirmService.sendMessage (message);
    }
}
