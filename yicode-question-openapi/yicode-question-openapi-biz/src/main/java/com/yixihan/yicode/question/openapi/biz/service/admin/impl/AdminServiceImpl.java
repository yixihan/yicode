package com.yixihan.yicode.question.openapi.biz.service.admin.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.yixihan.yicode.common.enums.DateDimensionEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.response.admin.BrokenDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.admin.CommitDataDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.AdminDataReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.BrokenDataVO;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.CommitDataVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 管理中心 服务实现类
 *
 * @author yixihan
 * @date 2023/3/3 22:09
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Override
    public List<BrokenDataVO> brokenData(AdminDataReq req) {
        AdminDataDtoReq dtoReq = getDtoReq (req);
    
        Map<String, BrokenDataDtoResult> dtoResult = questionFeignClient.brokenData (dtoReq).getResult ();
        
        return dtoResult.values ().stream ()
                .map (item -> BeanUtil.toBean (item, BrokenDataVO.class))
                .collect(Collectors.toList());
                
    }
    
    @Override
    public CommitDataVO commitData(AdminDataReq req) {
    
        AdminDataDtoReq dtoReq = getDtoReq (req);
    
        CommitDataDtoResult dtoResult = questionFeignClient.commitData (dtoReq).getResult ();
        
        return BeanUtil.toBean (dtoResult, CommitDataVO.class);
    }
    
    private AdminDataDtoReq getDtoReq(AdminDataReq req) {
        AdminDataDtoReq dtoReq = BeanUtil.toBean (req, AdminDataDtoReq.class);
        
        // 计算时间范围
        DateTime endDate = DateUtil.date ();
        DateTime startDate;
        switch (DateDimensionEnums.valueOf (dtoReq.getDateDimension ())) {
            case WEEK: {
                startDate = DateUtil.beginOfWeek (endDate);
                break;
            }
            case MONTH: {
                startDate = DateUtil.beginOfMonth (endDate);
                break;
            }
            case YEAR: {
                startDate = DateUtil.beginOfYear (endDate);
                break;
            }
            default: {
                throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
            }
        }
        
        dtoReq.setStartDate (DateUtil.format (startDate, DatePattern.NORM_DATETIME_PATTERN));
        dtoReq.setEndDate (DateUtil.format (endDate, DatePattern.NORM_DATETIME_PATTERN));
        
        return dtoReq;
    }
}
