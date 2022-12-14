package com.yixihan.yicode.user.openapi.biz.service.msg.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.rabbitmq.client.Channel;
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
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import com.yixihan.yicode.user.openapi.api.enums.MsgTypeEnums;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.ReadMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserVO;
import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;
import com.yixihan.yicode.user.openapi.biz.feign.message.MessageFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.msg.UserMsgFiegnClient;
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
 * description
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
    private UserMsgFiegnClient userMsgFiegnClient;
    
    @Resource
    private MessageFeignClient messageFeignClient;
    
    @Override
    public CommonVO<Boolean> addMessage(AddMessageReq req) {
        // ????????????
        if (userService.verifyUserId (req.getReceiveUseId ())) {
            throw new BizException ("?????????????????????");
        }
        if (Arrays.stream (MsgTypeEnums.values ()).noneMatch ((o) -> o.getType ().equals (req.getMessageType ()))) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        UserVO user = userService.getUserInfo ().getUser ();
        
        String template = userMsgFiegnClient.getMessageTemplate (req.getMessageType ()).getResult ().getData ();
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
        
        CommonDtoResult<Boolean> dtoResult = userMsgFiegnClient.addMessage (dtoReq).getResult ();
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
        
        UserVO user = userService.getUserInfo ().getUser ();
        
        ReadMessageDtoReq dtoReq = CopyUtils.copySingle (ReadMessageDtoReq.class, req);
        dtoReq.setUserId (user.getUserId ());
        
        CommonDtoResult<Boolean> dtoResult = userMsgFiegnClient.readMessages (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<MessageDetailVO> messageDetail(PageReq req) {
        UserVO user = userService.getUserInfo ().getUser ();
        
        MessageDetailDtoReq dtoReq = CopyUtils.copySingle (MessageDetailDtoReq.class, req);
        dtoReq.setUserId (user.getUserId ());
        
        PageDtoResult<MessageDetailDtoResult> dtoResult = userMsgFiegnClient.messageDetail (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (dtoResult, (o) -> CopyUtils.copySingle (MessageDetailVO.class, o));
    }
    
    @Override
    public CommonVO<Integer> unReadMessageCount() {
        UserVO user = userService.getUserInfo ().getUser ();
        
        CommonDtoResult<Integer> dtoResult = userMsgFiegnClient.unReadMessageCount (user.getUserId ()).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    /**
     * ??????????????????api, ????????????<br>
     * ????????????
     */
    @Async
    public void sendMessage(String message) {
        MsgSendDtoReq dtoReq = new MsgSendDtoReq ();
        dtoReq.setData (message);
        dtoReq.setMessageId (UUID.randomUUID ().toString (Boolean.TRUE));
        CommonDtoResult<Boolean> dtoResult = messageFeignClient.send (dtoReq).getResult ();
        
        if (!dtoResult.getData ()) {
            log.error ("??????????????????!");
        }
    }
    
    /**
     * ???????????????, ???????????????, ??????????????????
     */
    @RabbitListener(queues = MessageConstant.MESSAGE_QUEUE_NAME)
    public void receiveConfirmMessage(Message message, Channel channel) {
        try {
            // TODO ????????????????????????????????????
            log.info ("?????????????????? confirm.queue ?????? : {}", new String (message.getBody ()));
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.info ("???????????? : {}", e.getMessage ());
            throw new RuntimeException (e);
        }
    }
}
