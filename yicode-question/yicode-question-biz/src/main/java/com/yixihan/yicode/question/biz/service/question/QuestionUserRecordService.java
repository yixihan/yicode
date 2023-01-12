package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserRecordDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionUserRecord;

/**
 * <p>
 * 用户题目通过记录表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface QuestionUserRecordService extends IService<QuestionUserRecord> {
    
    /**
     * 添加用户题目通过记录
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addQuestionUserRecord(AddQuestionUserRecordDtoReq dtoReq);
    
    /**
     * 获取用户题目通过记录详情
     *
     * @param id 主键 id
     */
    UserRecordDtoResult questionUserRecordDetail(Long id);
    
    /**
     * 获取用户题目通过记录
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<UserRecordDtoResult> queryQuestionUserRecord(QueryUserRecordDtoReq dtoReq);
}
