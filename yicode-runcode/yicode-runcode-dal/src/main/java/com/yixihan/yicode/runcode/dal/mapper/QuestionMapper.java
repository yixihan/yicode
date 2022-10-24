package com.yixihan.yicode.runcode.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.runcode.dal.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 问题表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-10-24
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}
