package com.yixihan.yicode.user.openapi.biz.service.msg.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yixihan.yicode.common.constant.MessageConstant;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.MessageDetailDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.ReadMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.ReadMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;
import com.yixihan.yicode.user.openapi.biz.feign.message.MessageFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.msg.UserMsgFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.msg.SseEmitterService;
import com.yixihan.yicode.user.openapi.biz.service.msg.UserMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    private SseEmitterService sseEmitterService;
    
    @Resource
    private UserMsgFeignClient userMsgFeignClient;
    
    @Resource
    private MessageFeignClient messageFeignClient;
    
    @Override
    public MessageDetailVO addMessage(AddMessageReq req) {
        // 参数校验
        if (Arrays.stream (MsgTypeEnums.values ()).noneMatch (o -> o.getType ().equals (req.getMessageType ()))) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 获取用户信息
        UserDetailInfoVO user = userService.getUserDetailInfo ();
        
        // 填充消息
        String template = userMsgFeignClient.getMessageTemplate (req.getMessageType ()).getResult ();
        String message = StrUtil.format (template, user.getUser ().getUserName ());
        
        
        // 保存消息
        AddMessageDtoReq dtoReq = BeanUtil.toBean (req, AddMessageDtoReq.class);
        dtoReq.setMsg (message);
        dtoReq.setSendUserId (user.getUser ().getUserId ());
        dtoReq.setSendUserName (user.getUser ().getUserName ());
        MessageDetailDtoResult dtoResult = userMsgFeignClient.addMessage (dtoReq).getResult ();
        dtoResult.setSendUserAvatar (user.getUserInfo ().getUserAvatar ());
        
        // 发送消息
        sendMessage (JSONUtil.toJsonStr (dtoResult));
        return BeanUtil.toBean (dtoResult, MessageDetailVO.class);
    }
    
    @Override
    public void readMessages(ReadMessageReq req) {
        // 参数校验
        Assert.isTrue (CollUtil.isNotEmpty (req.getMessageIdList ()), BizCodeEnum.PARAMS_VALID_ERR);
        
        // 阅读消息
        ReadMessageDtoReq dtoReq = BeanUtil.toBean (req, ReadMessageDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        userMsgFeignClient.readMessages (dtoReq);
    }
    
    @Override
    public PageVO<MessageDetailVO> messageDetail(PageReq req) {
        MessageDetailDtoReq dtoReq = BeanUtil.toBean (req, MessageDetailDtoReq.class);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        
        PageDtoResult<MessageDetailDtoResult> dtoResult = userMsgFeignClient.messageDetail (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, MessageDetailVO.class)
        );
    }
    
    @Override
    public Integer unReadMessageCount() {
        return userMsgFeignClient.unReadMessageCount (userService.getUser ().getUserId ()).getResult ();
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
    
    /**
     * 消息消费者, 收到消息后, 主动推给前端
     */
    @RabbitListener(queues = MessageConstant.MESSAGE_QUEUE_NAME)
    public void receiveConfirmMessage(Message message, Channel channel) {
        try {
            MessageDetailVO vo = JSONUtil.toBean (new String (message.getBody ()), MessageDetailVO.class );
            log.info ("接受到的队列 confirm.queue 消息 : {}", vo);
            sseEmitterService.sendMsgToClient (CollUtil.newArrayList (vo));
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.info ("出现异常 : {}", e.getMessage ());
            throw new BizException (e);
        }
    }
}
