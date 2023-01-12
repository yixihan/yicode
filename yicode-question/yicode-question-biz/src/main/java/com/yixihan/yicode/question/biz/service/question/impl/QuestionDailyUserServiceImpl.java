package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.question.api.dto.request.question.MonthUserDailyQuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.AddUserDailyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserDailyQuestionDtoResult;
import com.yixihan.yicode.question.biz.service.question.QuestionDailyUserService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionDailyUserMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDailyUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户每日一题表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionDailyUserServiceImpl extends ServiceImpl<QuestionDailyUserMapper, QuestionDailyUser> implements QuestionDailyUserService {
    
    @Override
    public CommonDtoResult<Boolean> addUserDailyQuestion(AddUserDailyQuestionDtoReq dtoReq) {
        return null;
    }
    
    @Override
    public List<UserDailyQuestionDtoResult> monthUserDailyQuestionDetail(MonthUserDailyQuestionDetailDtoReq dtoReq) {
        return null;
    }
}
