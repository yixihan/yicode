package com.yixihan.yicode.question.dal.mapper.note;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.question.dal.pojo.note.Note;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 问题题解表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}
