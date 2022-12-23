package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLabelDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLabelDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserLabel;
import com.yixihan.yicode.user.dal.mapper.extra.UserLabelMapper;
import com.yixihan.yicode.user.biz.service.extra.UserLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户标签表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-12-21
 */
@Service
public class UserLabelServiceImpl extends ServiceImpl<UserLabelMapper, UserLabel> implements UserLabelService {
    
    
    @Override
    public CommonDtoResult<Boolean> addUserLabel(ModifyUserLabelDtoReq dtoReq) {
        // TODO 校验用户标签是否存在
        
        // 添加标签
        UserLabel label = BeanUtil.toBean (dtoReq, UserLabel.class);
        int modify = baseMapper.insert (label);
        if (modify == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }
    
    @Override
    public List<UserLabelDtoResult> getUserLabel(Long userId) {
        List<UserLabel> userLabelList = baseMapper.selectList (new QueryWrapper<UserLabel> ()
                .eq (UserLabel.USER_ID, userId));
        
        userLabelList = Optional.ofNullable (userLabelList)
                .orElse (Collections.emptyList ());
        
        // TODO 根据 label ID 获取 label 名
        return CopyUtils.copyMulti (UserLabelDtoResult.class, userLabelList);
    }
}
