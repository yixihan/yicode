package com.yixihan.yicode.question.biz.service.question;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.question.api.dto.request.question.AddUserDailyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserDailyQuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.UserDailyQuestionDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDailyUser;

import java.util.List;

/**
 * <p>
 * 用户每日一题表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
public interface QuestionDailyUserService extends IService<QuestionDailyUser> {
    
    /**
     * 添加每日一题用户做题情况
     *
     * @param dtoReq 请求参数
     * @return {@link UserDailyQuestionDtoResult}
     */
    UserDailyQuestionDtoResult addUserDailyQuestion(AddUserDailyQuestionDtoReq dtoReq);
    
    /**
     * 获取用户连续做每日一题的天数
     *
     * @param userId 用户 ID
     * @return 用户连续做每日一题的天数
     */
    Integer dailyQuestionCount(Long userId);
    
    /**
     * 查询用户每月每日一题情况
     *
     * @param dtoReq 请求参数
     * @return {@link UserDailyQuestionDtoResult}
     */
    List<UserDailyQuestionDtoResult> monthUserDailyQuestionDetail(UserDailyQuestionDetailDtoReq dtoReq);
}
