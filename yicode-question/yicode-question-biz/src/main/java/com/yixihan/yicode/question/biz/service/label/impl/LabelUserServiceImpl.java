package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelUserDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.biz.service.label.LabelService;
import com.yixihan.yicode.question.biz.service.label.LabelUserService;
import com.yixihan.yicode.question.dal.mapper.label.LabelUserMapper;
import com.yixihan.yicode.question.dal.pojo.label.LabelUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    
    @Resource
    private LabelService labelService;
    
    @Override
    public CommonDtoResult<Boolean> addUserLabel(ModifyLabelUserDtoReq dtoReq) {
        List<LabelUser> labelUserList = dtoReq.getLabelId ().stream ()
                .map ((labelId) -> {
                    LabelUser labelUser = new LabelUser ();
                    labelUser.setLabelId (labelId);
                    labelUser.setUserId (dtoReq.getUserId ());
                    return labelUser;
                }).collect(Collectors.toList());
    
        boolean modify = this.saveBatch (labelUserList);
    
        if (modify) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> delUserLabel(ModifyLabelUserDtoReq dtoReq) {
        int modify = baseMapper.delete (new QueryWrapper<LabelUser> ()
                .eq (LabelUser.USER_ID, dtoReq.getUserId ())
                .eq (LabelUser.LABEL_ID, dtoReq.getLabelId ()));
        
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<LabelDtoResult> userLabelDetail(Long userId) {
        List<LabelUser> labelUserList = baseMapper.selectList (new QueryWrapper<LabelUser> ()
                .eq (LabelUser.USER_ID, userId));
    
        return labelService.labelDetail (labelUserList.stream ()
                .map (LabelUser::getLabelId).collect (Collectors.toList ()));
    }
}
