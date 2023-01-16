package com.yixihan.yicode.question.biz.service.label.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.label.AddLabelDtoReq;
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
    public CommonDtoResult<Boolean> addLabel(AddLabelDtoReq dtoReq) {
        Label label = BeanUtil.toBean (dtoReq, Label.class);
    
        int modify = baseMapper.insert (label);
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delLabel(List<Long> labelIdList) {
        int modify = baseMapper.deleteBatchIds (labelIdList);
        
        if (modify < 0) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<LabelDtoResult> labelDetail(List<Long> labelIdList) {
        List<Label> labelList = baseMapper.selectBatchIds (labelIdList);
        
        return BeanUtil.copyToList (labelList, LabelDtoResult.class);
    }
    
    @Override
    public CommonDtoResult<Boolean> verifyLabel(Long labelId) {
        return new CommonDtoResult<> (baseMapper.selectCount (new QueryWrapper<Label> ()
                .eq (Label.LABEL_ID, labelId)) > 0);
    }
}
