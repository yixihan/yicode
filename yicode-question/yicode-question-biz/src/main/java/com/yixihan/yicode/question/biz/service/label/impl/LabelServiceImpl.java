package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelService;
import com.yixihan.yicode.question.dal.mapper.label.LabelMapper;
import com.yixihan.yicode.question.dal.pojo.label.Label;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {
    
    @Override
    public CommonDtoResult<Boolean> addLabel(String labelName) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delLabel(Long labelId) {
        return null;
    }
    
    @Override
    public List<LabelDtoResult> labelDetail(List<Long> labelIdList) {
        return null;
    }
}
