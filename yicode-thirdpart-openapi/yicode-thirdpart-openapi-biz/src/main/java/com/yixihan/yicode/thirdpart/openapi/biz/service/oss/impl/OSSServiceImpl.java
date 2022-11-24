package com.yixihan.yicode.thirdpart.openapi.biz.service.oss.impl;

import cn.hutool.core.date.DateUtil;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSPolicyDtoReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.oss.OSSFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.oss.OSSService;
import com.yixihan.yicode.thirdpart.openapi.biz.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * oss 模块 服务实现类
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@Slf4j
@Service
public class OSSServiceImpl implements OSSService {

    @Resource
    private OSSFeignClient ossFeignClient;

    @Resource
    private UserService userService;

    @Override
    public Map<String, String> policy() {
        OSSPolicyDtoReq dtoReq = new OSSPolicyDtoReq ();
        dtoReq.setFileDir (DateUtil.formatDate (new Date ()));
        dtoReq.setUserId (userService.getUserInfo ().getUserId ());
        return ossFeignClient.policy (dtoReq).getResult ();
    }
}
