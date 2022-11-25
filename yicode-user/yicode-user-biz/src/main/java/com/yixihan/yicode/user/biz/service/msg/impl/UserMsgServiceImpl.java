package com.yixihan.yicode.user.biz.service.msg.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.msg.UserMsgService;
import com.yixihan.yicode.user.dal.mapper.msg.UserMsgMapper;
import com.yixihan.yicode.user.dal.pojo.msg.UserMsg;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户消息表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserMsgServiceImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements UserMsgService {

}
