package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelNoteService;
import com.yixihan.yicode.question.dal.mapper.label.LabelNoteMapper;
import com.yixihan.yicode.question.dal.pojo.label.LabelNote;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题解标签表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class LabelNoteServiceImpl extends ServiceImpl<LabelNoteMapper, LabelNote> implements LabelNoteService {
    
    @Override
    public CommonDtoResult<Boolean> addNoteLabel(String noteLabelName) {
        return null;
    }
    
    @Override
    public CommonDtoResult<Boolean> delNoteLabel(Long noteLabelId) {
        return null;
    }
    
    @Override
    public List<LabelDtoResult> noteLabelDetail(List<Long> noteLabelIdList) {
        return null;
    }
}
