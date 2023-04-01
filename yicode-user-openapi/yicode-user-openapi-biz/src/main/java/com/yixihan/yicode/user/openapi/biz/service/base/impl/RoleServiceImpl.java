package com.yixihan.yicode.user.openapi.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.UserRoleQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.base.ModifyUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.UserRoleQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.RoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.UserRoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.RoleService;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:43
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    
    @Resource
    private RoleFeignClient roleFeignClient;
    
    @Resource
    private UserRoleFeignClient userRoleFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public List<RoleVO> addUserRole(ModifyUserRoleReq req) {
        // 校验角色 ID 是否合规(需存在)
        Assert.isTrue (roleFeignClient.validateRole (req.getRoleId ()).getResult (),
                new BizException ("该角色不存在!"));
        // 校验用户 ID 是否合规(需存在)
        Assert.isTrue (userService.verifyUserId (req.getUserId ()),
                new BizException ("该用户不存在!"));
        
        // 用户添加角色
        ModifyUserRoleDtoReq dtoReq = BeanUtil.toBean (req, ModifyUserRoleDtoReq.class);
        List<RoleDtoResult> dtoResults = userRoleFeignClient.addRole (dtoReq).getResult ();
        return BeanUtil.copyToList (dtoResults, RoleVO.class);
    }
    
    @Override
    public List<RoleVO> delUserRole(ModifyUserRoleReq req) {
        // 校验角色 ID 是否合规(需存在)
        Assert.isTrue (roleFeignClient.validateRole (req.getRoleId ()).getResult (),
                new BizException ("该角色不存在!"));
        // 校验用户 ID 是否合规(需存在)
        Assert.isTrue (userService.verifyUserId (req.getUserId ()),
                new BizException ("该用户不存在!"));
        
        // 删除用户角色
        ModifyUserRoleDtoReq dtoReq = new ModifyUserRoleDtoReq (req.getUserId (), req.getRoleId ());
        List<RoleDtoResult> dtoResults = userRoleFeignClient.delRole (dtoReq).getResult ();
        return BeanUtil.copyToList (dtoResults, RoleVO.class);
    }
    
    @Override
    public PageVO<RoleVO> getRolePage(PageReq req) {
        PageDtoReq dtoReq = BeanUtil.toBean (req, PageDtoReq.class);
        PageDtoResult<RoleDtoResult> dtoResult = roleFeignClient.rolePageDetail (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, RoleVO.class)
        );
    }
    
    @Override
    public PageVO<RoleVO> getUserRolePage(UserRoleQueryReq req) {
        UserRoleQueryDtoReq dtoReq = BeanUtil.toBean (req, UserRoleQueryDtoReq.class);
        PageDtoResult<RoleDtoResult> dtoResult = userRoleFeignClient.getUserRolePage (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, RoleVO.class)
        );
    }
}
