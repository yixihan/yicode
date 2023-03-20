package com.yixihan.yicode.question.biz.service.label.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
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
    public List<LabelDtoResult> addUserLabel(ModifyLabelUserDtoReq dtoReq) {
        List<LabelUser> labelUserList = dtoReq.getLabelId ()
                .stream ()
                .map (labelId -> assembleLabelUser (dtoReq.getUserId (), labelId))
                .collect(Collectors.toList());
        
        // 保存
        Assert.isTrue (saveBatch (labelUserList), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return userLabelDetail (dtoReq.getUserId ());
    }
    
    @Override
    public List<LabelDtoResult> delUserLabel(ModifyLabelUserDtoReq dtoReq) {
        Long id = lambdaQuery ()
                .select (LabelUser::getId)
                .eq (LabelUser::getUserId, dtoReq.getUserId ())
                .eq (LabelUser::getLabelId, dtoReq.getLabelId ())
                .one ()
                .getId ();
        Assert.notNull (id, new BizException ("该标签不存在"));
    
        // 删除
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
        return userLabelDetail (dtoReq.getUserId ());
    }
    
    @Override
    public List<LabelDtoResult> userLabelDetail(Long userId) {
        // 获取标签 id
        List<Long> labelIdList = lambdaQuery ()
                .eq (LabelUser::getUserId, userId)
                .orderByDesc (LabelUser::getCreateTime)
                .list ()
                .stream ()
                .map (LabelUser::getLabelId)
                .collect (Collectors.toList ());
    
        return labelService.labelDetail (labelIdList);
    }
    
    /**
     * 组装 {@link LabelUser}
     *
     * @param userId 用户 id
     * @param labelId 标签 id
     * @return {@link LabelUser}
     */
    private LabelUser assembleLabelUser(Long userId, Long labelId) {
        LabelUser labelUser = new LabelUser ();
        labelUser.setLabelId (labelId);
        labelUser.setUserId (userId);
        return labelUser;
    }
}
