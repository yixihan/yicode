package com.yixihan.yicode.question.dal.mapper.label;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.dal.pojo.label.LabelQuestion;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 问题标签表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface LabelQuestionMapper extends BaseMapper<LabelQuestion> {

}
