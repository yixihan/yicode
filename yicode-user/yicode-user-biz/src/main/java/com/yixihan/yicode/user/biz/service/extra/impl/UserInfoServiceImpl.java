package com.yixihan.yicode.user.biz.service.extra.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.dal.mapper.extra.UserInfoMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
