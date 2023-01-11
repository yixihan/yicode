package com.yixihan.yicode.question.dal.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionUserRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户题目通过记录表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface QuestionUserRecordMapper extends BaseMapper<QuestionUserRecord> {

}
