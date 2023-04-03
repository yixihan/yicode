package com.yixihan.yicode.question.openapi.biz.service.message.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.question.openapi.api.vo.request.AddMessageReq;
import com.yixihan.yicode.question.openapi.biz.feign.message.MessageFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.msg.UserMsgFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.message.UserMsgService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 消息模块 服务实现类
 *
 * @author yixihan
 * @date 2023/1/10 16:53
 */
@Slf4j
@Service
public class UserMsgServiceImpl implements UserMsgService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private UserMsgFeignClient userMsgFeignClient;
    
    @Resource
    private MessageFeignClient messageFeignClient;
    
    @Override
    public void addMessage(AddMessageReq req) {
        // 参数校验
        Assert.isTrue (userService.verifyUserId (req.getReceiveUseId ()), BizCodeEnum.ACCOUNT_NOT_FOUND);
        Assert.isFalse (Arrays.stream (MsgTypeEnums.values ()).noneMatch (o ->
                o.getType ().equals (req.getMessageType ())));
        
        // 构建消息内容
        UserCommonDtoResult user = userService.getUserCommonInfo (CollUtil.toList (userService.getUserId ())).get (0);
        AddMessageDtoReq dtoReq = BeanUtil.toBean (req, AddMessageDtoReq.class);
    
        String template = userMsgFeignClient.getMessageTemplate (req.getMessageType ()).getResult ();
        String message = StrUtil.format (template, user.getUserName ());
    
        // 构建请求 body
        dtoReq.setMsg (message);
        dtoReq.setSendUserId (user.getUserId ());
        dtoReq.setSendUserName (user.getUserName ());
        dtoReq.setSendUserAvatar (user.getUserAvatar ());
        
        // 保存消息
        MessageDetailDtoResult dtoResult = userMsgFeignClient.addMessage (dtoReq).getResult ();
    
        // 保存成功, 发送消息给用户
        sendMessage (JSONUtil.toJsonStr (dtoResult));
    }
    
    
    /**
     * 调用消息系统api, 发送消息<br>
     * 异步任务
     */
    @Async
    public void sendMessage(String message) {
        MsgSendDtoReq dtoReq = new MsgSendDtoReq ();
        dtoReq.setData (message);
        dtoReq.setMessageId (UUID.randomUUID ().toString (Boolean.TRUE));
        messageFeignClient.send (dtoReq);
    }
}
