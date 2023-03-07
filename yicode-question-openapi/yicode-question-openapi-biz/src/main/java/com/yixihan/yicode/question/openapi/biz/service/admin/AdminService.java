package com.yixihan.yicode.question.openapi.biz.service.admin;

import com.yixihan.yicode.question.openapi.api.vo.request.admin.AdminDataReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.BrokenDataVO;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.CommitDataVO;

import java.util.List;

/**
 * 管理中心 服务类
 *
 * @author yixihan
 * @date 2023/3/3 22:08
 */
public interface AdminService {
    
    /**
     * 网址数据获取
     *
     * @param req 请求参数
     * @return {@link BrokenDataVO}
     */
    List<BrokenDataVO> brokenData(AdminDataReq req);
    
    /**
     * 代码提交数据获取
     *
     * @param req 请求参数
     * @return {@link CommitDataVO}
     */
    CommitDataVO commitData(AdminDataReq req);
}
