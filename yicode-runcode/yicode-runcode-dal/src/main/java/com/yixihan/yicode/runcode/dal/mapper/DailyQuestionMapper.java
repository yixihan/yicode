package com.yixihan.yicode.runcode.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.runcode.dal.pojo.DailyQuestion;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 每日一题表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-10-24
 */
@Mapper
public interface DailyQuestionMapper extends BaseMapper<DailyQuestion> {

}
