package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelUserService;
import com.yixihan.yicode.question.dal.mapper.label.LabelUserMapper;
import com.yixihan.yicode.question.dal.pojo.label.LabelUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户标签表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class LabelUserServiceImpl extends ServiceImpl<LabelUserMapper, LabelUser> implements LabelUserService {
    
    @Override
    public CommonDtoResult<Boolean> addUserLabel(String userLabelName) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delUserLabel(Long userLabelId) {
        return null;
    }
    
    @Override
    public List<LabelDtoResult> userLabelDetail(List<Long> userLabelIdList) {
        return null;
    }
}
