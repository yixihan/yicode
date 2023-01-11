package com.yixihan.yicode.question.dal.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionDailyUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户每日一题表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface QuestionDailyUserMapper extends BaseMapper<QuestionDailyUser> {

}
