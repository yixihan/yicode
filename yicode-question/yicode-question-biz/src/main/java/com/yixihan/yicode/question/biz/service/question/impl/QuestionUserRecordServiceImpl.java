package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryUserRecordDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserRecordDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionUserRecordService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionUserRecordMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionUserRecord;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户题目通过记录表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionUserRecordServiceImpl extends ServiceImpl<QuestionUserRecordMapper, QuestionUserRecord> implements QuestionUserRecordService {
    
    @Override
    public CommonDtoResult<Boolean> addQuestionUserRecord(AddQuestionUserRecordDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public UserRecordDtoResult questionUserRecordDetail(Long id) {
        return null;
    }
    
    @Override
    public PageDtoResult<UserRecordDtoResult> queryQuestionUserRecord(QueryUserRecordDtoReq dtoReq) {
        return null;
    }
}
