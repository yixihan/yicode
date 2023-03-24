package com.yixihan.yicode.question.biz.service.label.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.question.api.dto.request.label.AddLabelDtoReq;
import com.yixihan.yicode.question.api.dto.request.label.QueryLabelDtoReq;
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
    public LabelDtoResult addLabel(AddLabelDtoReq dtoReq) {
        Label label = BeanUtil.toBean (dtoReq, Label.class);
    
        // 删除
        Assert.isTrue (save (label), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return BeanUtil.toBean (label, LabelDtoResult.class);
    }
    
    @Override
    public void delLabel(List<Long> labelIdList) {
        Assert.isTrue (removeByIds (labelIdList), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public List<LabelDtoResult> labelDetail(List<Long> labelIdList) {
        List<Label> labelList = listByIds (labelIdList);
        
        return BeanUtil.copyToList (labelList, LabelDtoResult.class);
    }
    
    @Override
    public PageDtoResult<LabelDtoResult> allLabel(QueryLabelDtoReq dtoReq) {
        Page<Label> page = lambdaQuery ()
                .like (StrUtil.isNotBlank (dtoReq.getLabelName ()), Label::getLabelName, dtoReq.getLabelName ())
                .orderByDesc (Label::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, LabelDtoResult.class)
        );
    }
    
    @Override
    public Boolean verifyLabel(Long labelId) {
        return lambdaQuery ()
                .eq (Label::getLabelId, labelId)
                .count () > 0;
    }
}
