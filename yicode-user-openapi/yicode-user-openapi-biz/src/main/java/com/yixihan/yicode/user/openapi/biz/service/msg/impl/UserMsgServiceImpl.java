package com.yixihan.yicode.user.openapi.biz.service.msg.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.rabbitmq.client.Channel;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.message.api.constant.MessageConstant;
import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.MessageDetailDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.ReadMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.ReadMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;
import com.yixihan.yicode.user.openapi.biz.feign.message.MessageFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.msg.UserMsgFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
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
    private UserMsgFeignClient userMsgFeignClient;
    
    @Resource
    private MessageFeignClient messageFeignClient;
    
    @Override
    public CommonVO<Boolean> addMessage(AddMessageReq req) {
        // 参数校验
        if (!userService.verifyUserId (req.getReceiveUseId ())) {
            throw new BizException ("该用户不存在！");
        }
        if (Arrays.stream (MsgTypeEnums.values ()).noneMatch ((o) -> o.getType ().equals (req.getMessageType ()))) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        UserDtoResult user = userService.getUser ();
        
        String template = userMsgFeignClient.getMessageTemplate (req.getMessageType ()).getResult ().getData ();
        String message;
        if (MsgTypeEnums.LIKE.getType ().equals (req.getMessageType ()) || MsgTypeEnums.REPLY.getType ().equals (req.getMessageType ())) {
            message = StrUtil.format (template, user.getUserName (), req.getSourceId ());
        } else {
            message = StrUtil.format (template, user.getUserName ());
        }
        AddMessageDtoReq dtoReq = CopyUtils.copySingle (AddMessageDtoReq.class, req);
        dtoReq.setMsg (message);
        dtoReq.setSendUserId (user.getUserId ());
        dtoReq.setSendUserName (user.getUserName ());
        
        CommonDtoResult<Boolean> dtoResult = userMsgFeignClient.addMessage (dtoReq).getResult ();
        if (dtoResult.getData ()) {
            sendMessage (message);
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> readMessages(ReadMessageReq req) {
        if (req.getMessageIdList () == null) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        ReadMessageDtoReq dtoReq = CopyUtils.copySingle (ReadMessageDtoReq.class, req);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        
        CommonDtoResult<Boolean> dtoResult = userMsgFeignClient.readMessages (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<MessageDetailVO> messageDetail(PageReq req) {
        MessageDetailDtoReq dtoReq = CopyUtils.copySingle (MessageDetailDtoReq.class, req);
        dtoReq.setUserId (userService.getUser ().getUserId ());
        
        PageDtoResult<MessageDetailDtoResult> dtoResult = userMsgFeignClient.messageDetail (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (dtoResult, (o) -> CopyUtils.copySingle (MessageDetailVO.class, o));
    }
    
    @Override
    public CommonVO<Integer> unReadMessageCount() {
        CommonDtoResult<Integer> dtoResult = userMsgFeignClient.unReadMessageCount (userService.getUser ().getUserId ()).getResult ();
        return CommonVO.create (dtoResult);
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
        CommonDtoResult<Boolean> dtoResult = messageFeignClient.send (dtoReq).getResult ();
        
        if (!dtoResult.getData ()) {
            log.error ("消息发送错误!");
        }
    }
    
    /**
     * 消息消费者, 收到消息后, 主动推给前端
     */
    @RabbitListener(queues = MessageConstant.MESSAGE_QUEUE_NAME)
    public void receiveConfirmMessage(Message message, Channel channel) {
        try {
            // TODO 接收到消息，主动推给前端
            log.info ("接受到的队列 confirm.queue 消息 : {}", new String (message.getBody ()));
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.info ("出现异常 : {}", e.getMessage ());
            throw new RuntimeException (e);
        }
    }
}
